package entities;

import java.time.LocalDateTime;

public class MessageVocal1 {
    private String sender;
    private String receiver;
    private String audioFilePath;
    private LocalDateTime timestamp;

    public MessageVocal1(String sender, String receiver, String audioFilePath, LocalDateTime timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.audioFilePath = audioFilePath;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
