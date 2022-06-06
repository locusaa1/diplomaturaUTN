package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.dataTransferObject.CallDTO;
import com.utn.diplomaturautn.exception.InvalidCallException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.repositroy.CallRepository;
import com.utn.diplomaturautn.service.CallService;
import com.utn.diplomaturautn.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CallServiceImpl implements CallService {

    private final CallRepository callRepository;

    @Autowired
    public CallServiceImpl(CallRepository callRepository) {

        this.callRepository = callRepository;
    }

    private List<Call> checkEmptyListThrowsException(Optional<List<Call>> callsList) {

        if (callsList.isEmpty()) {

            throw new NoContentException("There is not a register between the specifics dates");
        } else {

            return callsList.get();
        }
    }

    public List<Call> getAll() {

        return this.callRepository.findAll();
    }

    public Call getById(int id) {

        return this.callRepository.getById(id);
    }

    public List<Call> getByDateRangeAndUser(Timestamp from, Timestamp to, Phone clientPhone) {

        Utils.compareDatesThrowingExceptions(from, to);

        Optional<List<Call>> callsList = this.callRepository.findByStartDateGreaterThanEqualAndStartDateIsLessThanEqualAndOriginPhone(from, to, clientPhone);

        return this.checkEmptyListThrowsException(callsList);
    }

    public List<Call> getByDateRange(Timestamp from, Timestamp to) {

        Utils.compareDatesThrowingExceptions(from, to);

        Optional<List<Call>> callsList = this.callRepository.findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(from, to);

        return this.checkEmptyListThrowsException(callsList);
    }

    public Call addCall(Call newCall) {

        try {

            Call resultId = this.callRepository.save(newCall);
            this.callRepository.refresh(resultId);
            return resultId;
        } catch (Exception exception) {

            throw new InvalidCallException(exception.getMessage());
        }
    }
}
