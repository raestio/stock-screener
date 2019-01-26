package com.rasto.stockscreener.service;

import java.util.List;

public interface ConvertingService {
    <TO, FROM> TO convert(FROM source, Class<TO> destinationType);
    <TO, FROM> List<TO> convert(List<FROM> sourceList, Class<TO> destinationTypeInList);
}
