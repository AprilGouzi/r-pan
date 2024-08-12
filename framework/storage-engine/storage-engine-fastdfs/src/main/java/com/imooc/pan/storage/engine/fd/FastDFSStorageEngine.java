package com.imooc.pan.storage.engine.fd;

import com.imooc.pan.storage.engine.core.AbstractStorageEngine;
import com.imooc.pan.storage.engine.core.context.DeleteFileContext;
import com.imooc.pan.storage.engine.core.context.StoreFileChunkContext;
import com.imooc.pan.storage.engine.core.context.StoreFileContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Component
public class FastDFSStorageEngine extends AbstractStorageEngine {
    @Override
    protected void doStore(StoreFileContext context) throws IOException {

    }

    @Override
    public void delete(DeleteFileContext context) {

    }

    @Override
    protected void doStoreChunk(StoreFileChunkContext context) throws IOException {

    }

    @Override
    protected void doDelete(DeleteFileContext context) throws IOException {

    }
}
