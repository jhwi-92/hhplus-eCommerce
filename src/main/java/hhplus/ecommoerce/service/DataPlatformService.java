package hhplus.ecommoerce.service;

import hhplus.ecommoerce.infra.DataPlatformClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DataPlatformService {

    private final DataPlatformClient dataPlatformClient;

    public boolean sendDataPlatform() {
        dataPlatformClient.sendDataPlatform();
        return true;
    }

}
