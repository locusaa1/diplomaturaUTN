package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.repositroy.CallRepository;
import com.utn.diplomaturautn.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallServiceImpl implements CallService {

    private final CallRepository callRepository;

    @Autowired
    public CallServiceImpl(CallRepository callRepository) {

        this.callRepository = callRepository;
    }

    public List<Call> getAll() {

        return this.callRepository.findAll();
    }

    public Call getById(int id) {

        return this.callRepository.getById(id);
    }

    public Call addCall(Call newCall) {

        return this.callRepository.save(newCall);
    }
}
