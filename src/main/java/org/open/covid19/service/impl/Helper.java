package org.open.covid19.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.open.covid19.api.Covid19Api;
import org.open.covid19.entity.Case;
import org.open.covid19.mapper.Covid19ApiMapper;
import org.open.covid19.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class Helper {
    @Autowired
    private Covid19ApiMapper covid19ApiMapper;
    @Autowired
    private Covid19Api covid19Api;

    @Async
    public void insertCasesByIso2(String slug, boolean isFromDate) {
        int result = 0;
        long countryId = covid19ApiMapper.selectCountryId(slug);
        // 某国确诊数目
        List<Case> cases =isFromDate ? covid19Api.getCasesZFromDate(slug, DateUtil.local2tz("2020-04-29")) : covid19Api.getCases(slug);
        log.debug("该国家确诊数量:{}",cases.size());
        // 入库
        if (null != cases && cases.size() > 0) {
            result = covid19ApiMapper.setCasesList2country(countryId, cases);
        }
        if (result > 0) {
            log.debug("数据插入成功：{}","countryId:" + countryId);
        }
    }
}