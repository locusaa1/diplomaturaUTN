package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import com.utn.diplomaturautn.exception.InvalidCallException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Employee;
import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.repositroy.CallRepository;
import com.utn.diplomaturautn.service.CallService;
import com.utn.diplomaturautn.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CallServiceImpl implements CallService {

    private final CallRepository callRepository;

    @Autowired
    public CallServiceImpl(CallRepository callRepository) {

        this.callRepository = callRepository;
    }

    private List<Call> checkEmptyListThrowsException(List<Call> callsList) {

        if (callsList.isEmpty()) {

            throw new NoContentException("There is not a register between the specifics dates");
        } else {

            return callsList;
        }
    }

    public List<Call> getAll() {

        return this.callRepository.findAll();
    }

    public Call getById(int id) {

        return this.callRepository.getById(id);
    }

    public Call addCall(Call newCall, Client originClient) {

        if (!newCall.getOriginPhone().equals(newCall.getDestinationPhone())) {

            if (!newCall.getStartDate().after(Timestamp.valueOf(LocalDateTime.now()))) {

                if (originClient.getCondition().equals(ClientCondition.ACTIVE)) {

                    try {

                        Call resultId = this.callRepository.save(newCall);
                        this.callRepository.refresh(resultId);
                        return resultId;
                    } catch (Exception exception) {

                        throw new InvalidCallException(exception.getCause().getCause().toString());
                    }
                } else {

                    throw new InvalidCallException("The client that makes the call must be active.");
                }
            } else {

                throw new InvalidCallException("The date of the call must be before the current date and time");
            }
        } else {

            throw new InvalidCallException("You can not call your own phone.");
        }
    }

    public List<Call> getByDateRangeAndUser(String from, String to, Phone clientPhone, Authentication auth) {

        List<Call> callsList = this.getByDateRange(from, to, auth);

        return this.checkEmptyListThrowsException(
                callsList.stream()
                        .filter(c -> c.getOriginPhone().equals(clientPhone))
                        .collect(Collectors.toList()));
    }

    public List<Call> getByDateRange(String from, String to, Authentication auth) {

        Timestamp timestampFrom = Timestamp.valueOf(from + " 00:00:00");

        Timestamp timestampTo = (to.equals(LocalDate.now().toString())) ?
                Timestamp.valueOf(to.concat(" " + LocalTime.now().toString())) :
                Timestamp.valueOf(to + " 23:59:59");

        Utils.compareDatesThrowingExceptions(timestampFrom, timestampTo);

        Optional<List<Call>> callsList = this.callRepository.findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(timestampFrom, timestampTo);

        UserDetails user = (UserDetails) auth.getPrincipal();

        User userRequesting = new User(user.getUsername(), user.getPassword(), user.getAuthorities());
        //El auth no va en en este servicio sino en uno dedicado
        if (callsList.isPresent()) {

            List<Call> presentList = callsList.get();
            List<Call> finalList;
            if (userRequesting.getAuthorities().contains(new SimpleGrantedAuthority(UserType.CLIENT.toString()))) {

                Client clientRequesting = (Client) auth.getPrincipal();

                finalList = presentList.stream()
                        .filter(c -> c.getOriginPhone().equals(clientRequesting.getPhone()))
                        .collect(Collectors.toList());
            } else {

                finalList = presentList;
            }
            return this.checkEmptyListThrowsException(finalList);
        } else {

            throw new NoContentException("There are not calls with the specifics filters.");
        }
    }
}
