/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytqaproj.pojo;

/**
 *
 * @author DELL
 */
public class ExamPojo {
    String examId;
    String language;
    int totalquestions;

    public ExamPojo(String examId, String language, int totalquestions) {
        this.examId = examId;
        this.language = language;
        this.totalquestions = totalquestions;
    }
    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTotalquestions() {
        return totalquestions;
    }

    public void setTotalquestions(int totalquestions) {
        this.totalquestions = totalquestions;
    }
    
}
