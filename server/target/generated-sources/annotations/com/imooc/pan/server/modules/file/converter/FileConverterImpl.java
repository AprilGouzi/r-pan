package com.imooc.pan.server.modules.file.converter;

import com.imooc.pan.server.modules.file.context.CreateFolderContext;
import com.imooc.pan.server.modules.file.context.DeleteFileContext;
import com.imooc.pan.server.modules.file.context.SecUploadFileContext;
import com.imooc.pan.server.modules.file.context.UpdateFilenameContext;
import com.imooc.pan.server.modules.file.po.CreateFolderPO;
import com.imooc.pan.server.modules.file.po.DeleteFilePO;
import com.imooc.pan.server.modules.file.po.SecUploadFilePO;
import com.imooc.pan.server.modules.file.po.UpdateFilenamePO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-07T21:50:36+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class FileConverterImpl implements FileConverter {

    @Override
    public CreateFolderContext createFolderPO2CreateFolderContext(CreateFolderPO createFolderPO) {
        if ( createFolderPO == null ) {
            return null;
        }

        CreateFolderContext createFolderContext = new CreateFolderContext();

        createFolderContext.setFolderName( createFolderPO.getFolderName() );

        createFolderContext.setParentId( com.imooc.pan.core.utils.IdUtil.decrypt(createFolderPO.getParentId()) );
        createFolderContext.setUserId( com.imooc.pan.server.common.utils.UserIdUtil.get() );

        return createFolderContext;
    }

    @Override
    public DeleteFileContext deleteFilePO2DeleteFileContext(DeleteFilePO deleteFilePO) {
        if ( deleteFilePO == null ) {
            return null;
        }

        DeleteFileContext deleteFileContext = new DeleteFileContext();

        deleteFileContext.setUserId( com.imooc.pan.server.common.utils.UserIdUtil.get() );

        return deleteFileContext;
    }

    @Override
    public UpdateFilenameContext updateFilenamePO2UpdateFilenameContext(UpdateFilenamePO updateFilenamePO) {
        if ( updateFilenamePO == null ) {
            return null;
        }

        UpdateFilenameContext updateFilenameContext = new UpdateFilenameContext();

        updateFilenameContext.setFileId( com.imooc.pan.core.utils.IdUtil.decrypt(updateFilenamePO.getFileId()) );
        updateFilenameContext.setUserId( com.imooc.pan.server.common.utils.UserIdUtil.get() );

        return updateFilenameContext;
    }

    @Override
    public SecUploadFileContext secUploadFilePO2SecUploadFileContext(SecUploadFilePO secUploadFilePO) {
        if ( secUploadFilePO == null ) {
            return null;
        }

        SecUploadFileContext secUploadFileContext = new SecUploadFileContext();

        secUploadFileContext.setFilename( secUploadFilePO.getFilename() );
        secUploadFileContext.setIdentifier( secUploadFilePO.getIdentifier() );

        secUploadFileContext.setParentId( com.imooc.pan.core.utils.IdUtil.decrypt(secUploadFilePO.getParentId()) );
        secUploadFileContext.setUserId( com.imooc.pan.server.common.utils.UserIdUtil.get() );

        return secUploadFileContext;
    }
}
