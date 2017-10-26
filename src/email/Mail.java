/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

/**
 *
 * @author hieunq
 */
public class Mail {
    private int index;
    private String from;
    private String subject;
    private String content;
    private String sentTime;

    public Mail() {
    }

    public Mail(int index, String from, String subject, String content, String sentTime) {
        this.index = index;
        this.from = from;
        this.subject = subject;
        this.content = content;
        this.sentTime = sentTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }
    
    
}
