package proyectoserver;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.regex.Pattern;

public class ProyectoServer {

    //private static final int PORT_NUMBER = 8008;

    public void start() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        int PORT_NUMBER = 8008;
        try {
            System.out.println("Ingresa el puerto en el cual el servidor estara escuchando el puerto por default es el 8008");
            PORT_NUMBER = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            System.out.println("Ingresa un número");
        }
        System.out.println("Iniciando Servido en el puerto: " + PORT_NUMBER);
        try {
            ServerSocket s = new ServerSocket(PORT_NUMBER);
            for (;;) {
                Socket incoming = s.accept();
                System.out.println("Cliente conectado" + incoming.getInetAddress());
                new ClientHandler(incoming).start();
            }
        } catch (Exception e) {
        }
        System.out.println("Se detuvo el Servidor");
    }

    public static void main(String[] args) {
        new ProyectoServer().start();
    }

    private class ClientHandler extends Thread {

        private Socket incoming;

        public ClientHandler(Socket incoming) {
            this.incoming = incoming;
        }

        @Override
        public void run() {
            //PrintWriter out = null;
            try {
                //out = new PrintWriter(new OutputStreamWriter(incoming.getOutputStream()));
                //out.println("Exit");
                //out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
                for (;;) {
                    Robot robot = new Robot();
                    String Command = in.readLine();
                    if (Command == null) {
                        break;
                    } else {
                        if (Command.trim().equals("Exit")) {
                            System.out.println("Cliente desconectado");
                            break;
                        } else if (Command.trim().equals("PowerPoint")) {
                            PowerPoint(robot);
                        } else if (Command.trim().equals("WMP")) {
                            WMP(robot);
                        } else if (Command.trim().equals("Mouse")) {
                            Mouse(robot);
                        }
                    }
                }
                incoming.close();
            } catch (IOException | AWTException e) {
            }
        }

        public void PowerPoint(Robot robot) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
                for (;;) {
                    String Command = in.readLine();
                    if (Command.trim().equals("Exit")) {
                        break;
                    } else {
                        switch (Command.trim()) {
                            case "Der":
                                robot.keyPress(KeyEvent.VK_RIGHT);
                                robot.keyRelease(KeyEvent.VK_RIGHT);
                                break;
                            case "Izq":
                                robot.keyPress(KeyEvent.VK_LEFT);
                                robot.keyRelease(KeyEvent.VK_LEFT);
                                break;
                            case "F5":
                                robot.keyPress(KeyEvent.VK_F5);
                                robot.keyRelease(KeyEvent.VK_F5);
                                break;
                            case "Esc":
                                robot.keyPress(KeyEvent.VK_ESCAPE);
                                robot.keyRelease(KeyEvent.VK_ESCAPE);
                                break;
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("Problemas");
            }
        }

        private void WMP(Robot robot) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
                for (;;) {
                    String Command = in.readLine();
                    if (Command.trim().equals("Exit")) {
                        break;
                    } else {
                        switch (Command.trim()) {
                            case "Next":
                                robot.keyPress(KeyEvent.VK_CONTROL);
                                robot.keyPress(KeyEvent.VK_F);
                                robot.keyRelease(KeyEvent.VK_CONTROL);
                                robot.keyRelease(KeyEvent.VK_F);
                                break;
                            case "Back":
                                robot.keyPress(KeyEvent.VK_CONTROL);
                                robot.keyPress(KeyEvent.VK_B);
                                robot.keyRelease(KeyEvent.VK_CONTROL);
                                robot.keyRelease(KeyEvent.VK_B);
                                break;
                            case "Play":
                                robot.keyPress(KeyEvent.VK_CONTROL);
                                robot.keyPress(KeyEvent.VK_P);
                                robot.keyRelease(KeyEvent.VK_CONTROL);
                                robot.keyRelease(KeyEvent.VK_P);
                                break;
                            case "Stop":
                                robot.keyPress(KeyEvent.VK_CONTROL);
                                robot.keyPress(KeyEvent.VK_S);
                                robot.keyRelease(KeyEvent.VK_CONTROL);
                                robot.keyRelease(KeyEvent.VK_S);
                                break;
                            case "Pause":
                                robot.keyPress(KeyEvent.VK_CONTROL);
                                robot.keyPress(KeyEvent.VK_P);
                                robot.keyRelease(KeyEvent.VK_CONTROL);
                                robot.keyRelease(KeyEvent.VK_P);
                                break;
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("Problemas");
            }
        }

        private void Mouse(Robot robot) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
                int Aux_x = 0;
                int Aux_y = 0;
                for (;;) {
                    String Command = in.readLine();
                    if (Command.trim().equals("Exit")) {
                        break;
                    } else {
                        if (!Pattern.matches("[a-zA-Z]+", Command)) {
                        Point p = MouseInfo.getPointerInfo().getLocation();
                        int OriginalPosition_X = p.x;
                        int OriginalPosition_Y = p.y;

                        String[] Coordenadas = Command.split(" ");

                        int x = Math.round(Float.parseFloat(Coordenadas[0]));
                        int y = Math.round(Float.parseFloat(Coordenadas[1]));

                        if (x > Aux_x) {
                            if (y > Aux_y) {
                                robot.mouseMove(OriginalPosition_X + 5, OriginalPosition_Y + 5);
                            } else if (y < Aux_y) {
                                robot.mouseMove(OriginalPosition_X + 5, OriginalPosition_Y - 5);
                            } else {
                                robot.mouseMove(OriginalPosition_X + 5, OriginalPosition_Y);
                            }
                        } else if (x < Aux_x) {
                            if (y > Aux_y) {
                                robot.mouseMove(OriginalPosition_X - 5, OriginalPosition_Y + 5);
                            } else if (y < Aux_y) {
                                robot.mouseMove(OriginalPosition_X - 5, OriginalPosition_Y - 5);
                            } else {
                                robot.mouseMove(OriginalPosition_X - 5, OriginalPosition_Y);
                            }
                        } else if (x == Aux_x) {
                            if (y > Aux_y) {
                                robot.mouseMove(OriginalPosition_X, OriginalPosition_Y + 5);
                            } else if (y < Aux_y) {
                                robot.mouseMove(OriginalPosition_X, OriginalPosition_Y - 5);
                            } else {
                                robot.mouseMove(OriginalPosition_X, OriginalPosition_Y);
                            }
                        }
                        Aux_x = x;
                        Aux_y = y;
                    } else {
                            switch (Command) {
                                case "Der":
                                    robot.mousePress(InputEvent.BUTTON1_MASK);
                                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                                    break;
                                case "Izq":
                                    robot.mousePress(InputEvent.BUTTON3_MASK);
                                    robot.mouseRelease(InputEvent.BUTTON3_MASK);
                                    break;
                                default:
                                    System.out.println(Command);
                                    break;
                            }
                    }
                    }
                }
            } catch (IOException ex) {
                System.out.println("Problemas");
            }
        }
    }
}
