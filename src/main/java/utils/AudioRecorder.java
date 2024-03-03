package utils;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class AudioRecorder {
    private final AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
    private TargetDataLine line;

    public void startRecording(String filePath) throws LineUnavailableException {
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line not supported");
            return;
        }
        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        AudioInputStream ais = new AudioInputStream(line);

        new Thread(() -> {
            try {
                AudioSystem.write(ais, AudioFileFormat.Type.WAVE, new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopRecording() {
        if (line != null && line.isOpen()) {
            line.stop();
            line.close();
        }
    }
}
