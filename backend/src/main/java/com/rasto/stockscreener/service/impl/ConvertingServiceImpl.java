package com.rasto.stockscreener.service.impl;

import com.rasto.stockscreener.service.ConvertingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvertingServiceImpl implements ConvertingService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public <TO, FROM> TO convert(FROM source, Class<TO> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public <TO, FROM> List<TO> convert(List<FROM> sourceList, Class<TO> destinationTypeInList) {
        return sourceList.stream().map(source -> modelMapper.map(source, destinationTypeInList)).collect(Collectors.toList());
    }
}
