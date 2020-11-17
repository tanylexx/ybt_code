package com.sinosoft.surrender.addprem.batch;

import com.sinosoft.common.util.quartz.batch.MultThreadBatch;
import com.sinosoft.surrender.addprem.dto.AddPremImportDto;
import com.sinosoft.surrender.addprem.service.AddPremImportCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("addPremImportCoreBatchImpl")
public class AddPremImportCoreBatchImpl implements MultThreadBatch {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AddPremImportCoreService addPremImportCoreService;

    @Override
    public void executeInit() throws Exception {
        logger.info("--保费追加导核心批处理开始执行--");
    }

    @Override
    public List executeData() throws Exception {
        logger.info("--保费追加导核心批处理开始获取数据--");
        List<AddPremImportDto> addPremImportDtoList = addPremImportCoreService.getToBeImportedCont();
        return addPremImportDtoList;
    }

    @Override
    public void execute(Object o) throws Exception {
        AddPremImportDto addPremImportDto = (AddPremImportDto) o;
        addPremImportCoreService.importCore(addPremImportDto);
    }

    @Override
    public void executeFinal() throws Exception {
        logger.info("--保费追加导核心批处理执行结束--");
    }
}
