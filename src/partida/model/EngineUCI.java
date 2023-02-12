package partida.model;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class EngineUCI extends Thread {

    Process engine;
    ArrayList<String> movimentos;

    InputStream input;
    BufferedReader reader;

    OutputStream output;
    BufferedWriter writer;

    public EngineUCI(String executavel, int dificuldade) {
        try {
            String path = new File("engines\\" + executavel).getAbsolutePath();
            engine = Runtime.getRuntime().exec(path);

            input = engine.getInputStream();
            output = engine.getOutputStream();

            reader = new BufferedReader(new InputStreamReader(input));
            writer = new BufferedWriter(new OutputStreamWriter(output));

            // writer.write("setoption name UCI_LimitStrength value true\n");
            // writer.write("setoption name UCI_Elo value 400\n");
            
            writer.write("uci\n");
            writer.write("setoption name Skill Level value " + dificuldade + "\n");
            
            writer.flush();
            
            movimentos = new ArrayList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void receberMovimento(String movimento) {
        try {

            if (movimento != null) {
                movimentos.add(movimento);

                String parser = "";
                for (String jogado : movimentos) {
                    parser += jogado + " ";
                }

                writer.write("position startpos moves " + parser + "\n");
            } else {
                writer.write("ucinewgame\n");
            }
            
            writer.flush();

            new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1000);
                        writer.write("stop\n");
                        writer.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }.start();

            writer.write("go\n");
            writer.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String enviarMovimento() {
        try {

            String line, move = null;
            while ((line = reader.readLine()) != null) {
                if (line.split(" ")[0].equals("bestmove")) {
                    move = line.split(" ")[1];
                    break;
                }
            }

            System.out.println(move);
            movimentos.add(move);

            return move;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
