package modelo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Music extends Thread {

    private Clip clip = null;
    private JProgressBar estadoMusica;
    private String url;
    private AudioInputStream audioInputStream;
    private FloatControl gain;
    private JTextField tempoR;
    private int segundos;
    private JButton proximaMusica;
    private JTextField acabaEm;
    float volume;
    
    
    public Music(JProgressBar estado, String url, JTextField tempo, JButton proxima, JTextField acaba, float volume) {
        this.estadoMusica = estado;
        this.url = url;
        this.tempoR = tempo;
        this.proximaMusica = proxima;
        this.acabaEm = acaba;
        this.volume=volume;
    }

    public void run() {
        musica();
        clip.start();
        while (this.isAlive()) {
            frame();
        }
    }

    public void musica() {

        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(url));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            gc.add(Calendar.SECOND, (int) (clip.getMicrosecondLength() / 1000000));
            acabaEm.setText(sdf.format(gc.getTime()));
            volume(volume);
        } catch (Exception e) {
        }
    }

    public void stopMusica() {
        try{
        clip.stop();
        }
        catch(Exception ex){}
    }

    public void frame() {
        estadoMusica.setMinimum(0);
        estadoMusica.setMaximum(clip.getFrameLength());
        estadoMusica.setValue(clip.getFramePosition());
        segundos = (int) (clip.getMicrosecondLength() - clip.getMicrosecondPosition());
        segundos = segundos / 1000000;
        int minutos = segundos / 60;
        while (segundos > 59) {
            segundos = segundos - 60;
        }
        
        this.tempoR.setText("" + minutos + ':' + segundos);
    }

    public void volume(float db) {
        gain.setValue(db);
    }
}