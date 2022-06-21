package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.enumerated.UserType;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.repositroy.BillRepository;
import com.utn.diplomaturautn.service.BillService;
import com.utn.diplomaturautn.utils.Utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {

        this.billRepository = billRepository;
    }

    private List<Bill> checkEmptyListThrowsException(List<Bill> billList) {

        if (billList.isEmpty()) {

            throw new NoContentException("There is not a register between the specifics dates");
        } else {

            return billList;
        }
    }

    @Override
    public List<Bill> getAll() {

        List<Bill> billList = this.billRepository.findAll();

        if (!billList.isEmpty()) {

            return billList;
        } else {

            throw new NoContentException("There is no content in the database from this entity.");
        }
    }

    @Override
    public Bill getById(int id) {

        if (id > 0) {

            Optional<Bill> billFound = this.billRepository.findById(id);
            if (billFound.isPresent()) {

                return billFound.get();
            } else {

                throw new ResourceNotFoundException("There is not a register with the specific id");
            }
        } else {

            throw new InvalidBeanFieldsException("The id must be higher than 0");
        }
    }

    @Override
    public List<Bill> getByDateRangeAndClient(String from, String to, Client client, Authentication auth) {

        List<Bill> billList = this.getByDateRange(from, to, auth);

        return this.checkEmptyListThrowsException(
                billList.stream()
                        .filter(b -> b.getClient().getUsername().equals(client.getUsername()))
                        .collect(Collectors.toList()));
    }

    @Override
    public List<Bill> getByDateRange(String from, String to, Authentication auth) {

        Timestamp timestampFrom = Timestamp.valueOf(from + " 00:00:00");

        Timestamp timestampTo = (to.equals(LocalDate.now().toString())) ?
                Timestamp.valueOf(to.concat(" " + LocalTime.now().toString())) :
                Timestamp.valueOf(to + " 23:59:59");

        Utils.compareDatesThrowingExceptions(timestampFrom, timestampTo);

        UserDetails user = (UserDetails) auth.getPrincipal();

        User userRequesting = new User(user.getUsername(), user.getPassword(), user.getAuthorities());

        Optional<List<Bill>> billsList = this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(timestampFrom, timestampTo);

        if (billsList.isPresent()) {

            List<Bill> presentList = billsList.get();
            List<Bill> finalList;
            if (userRequesting.getAuthorities().contains(new SimpleGrantedAuthority(UserType.CLIENT.toString()))) {

                finalList = presentList.stream()
                        .filter(b -> b.getClient().getUsername().equals(userRequesting.getUsername()))
                        .collect(Collectors.toList());
            } else {

                finalList = presentList;
            }
            return this.checkEmptyListThrowsException(finalList);
        } else {

            throw new NoContentException("There are not bills with the specifics filters.");
        }
    }
}
