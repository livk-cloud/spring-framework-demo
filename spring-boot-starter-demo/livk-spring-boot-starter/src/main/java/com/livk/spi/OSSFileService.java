package com.livk.spi;

import java.io.InputStream;

/**
 * <p>
 * OSSFileService
 * </p>
 *
 * @author livk
 * @date 2022/3/18
 */
public class OSSFileService implements FileService {
    @Override
    public void upload(final InputStream inputStream) {

    }

    @Override
    public byte[] download(final String filename) {
        return new byte[0];
    }

    @Override
    public String getType() {
        return this.getClass().getName();
    }
}
