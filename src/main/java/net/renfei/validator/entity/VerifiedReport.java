package net.renfei.validator.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 验证报告
 *
 * @author renfei
 */
public class VerifiedReport {
    private Boolean passed;

    private String objectName;
    private Date verificationTime;
    List<VerifiedReportDetail> detailList;

    public VerifiedReport() {
        passed = true;
        verificationTime = new Date();
        detailList = new ArrayList<>();
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Date getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(Date verificationTime) {
        this.verificationTime = verificationTime;
    }

    public List<VerifiedReportDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<VerifiedReportDetail> detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return "VerifiedReport{\n" +
                "\tpassed=" + passed +
                ",\n\tobjectName='" + objectName + '\'' +
                ",\n\tverificationTime=" + verificationTime +
                ",\n\tdetailList=" + detailList +
                "}\n";
    }
}
