package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Province;
import com.utn.diplomaturautn.repositroy.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    private ProvinceRepository provinceRepository;

    @Autowired
    public void ProvinceRepository(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public void addProvince(Province province) {
        this.provinceRepository.save(province);
    }

    public List<Province> getAll(){
        return this.provinceRepository.findAll();
    }

    public Province getById(int id){
        return this.provinceRepository.getById(id);
    }
}
