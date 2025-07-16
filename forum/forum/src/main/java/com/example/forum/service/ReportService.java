package com.example.forum.service;

import com.example.forum.controller.form.ReportForm;
import com.example.forum.repository.ReportRepository;
import com.example.forum.repository.entity.Report;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    /*
     * レコード全件取得処理→if文つけてみる
     */
    public List<ReportForm> findAllReport() {
//        if (!StringUtils.isEmpty(startDate)) {
//            startDate += " 00:00:00";
//        } else {
//            startDate = " 2025/06/01 00:00:00";
//        }
//
//        //もしendDateに値があったらその値 + " 23:59:59"をDaoに渡したい
//        if (!StringUtils.isEmpty(endDate)) {
//            endDate += " 23:59:59";
//        } else {
//            //変数dateを宣言して、フォーマット変換してる
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//
//            //変数endにsdfからfomatメソッドで引数dateを渡したものを代入してる
//            endDate = sdf.format(date);
//        }
        List<Report> results = reportRepository.findAllByOrderByIdDesc();
        List<ReportForm> reports = setReportForm(results);
        return reports;
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<ReportForm> setReportForm(List<Report> results) {
        List<ReportForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ReportForm report = new ReportForm();
            Report result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            reports.add(report);
        }
        return reports;
    }

    /*
     * レコード追加
     */
    public void saveReport(ReportForm reqReport) {
        Report saveReport = setReportEntity(reqReport);
        reportRepository.save(saveReport);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Report setReportEntity(ReportForm reqReport) {
        Report report = new Report();
        report.setId(reqReport.getId());
        report.setContent(reqReport.getContent());
        report.setCreateDate(reqReport.getCreateDate());
        report.setUpdateDate(reqReport.getUpdateDate());
        return report;
    }
    /*
     * レコード削除
     */
    public void deleteReport(Integer id) {
        reportRepository.deleteById(id);
    }

    /*
     * レコード1件取得
     */
    public ReportForm editReport(Integer id) {
        List<Report> results = new ArrayList<>();
        results.add((Report) reportRepository.findById(id).orElse(null));
        List<ReportForm> reports = setReportForm(results);
        return reports.get(0);
    }
}
