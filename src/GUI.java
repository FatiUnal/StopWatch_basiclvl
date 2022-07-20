import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    JList<String> list;
    DefaultListModel<String> l;
    JLabel lbl;
    JButton startBtn,resetBtn,checkBtn;
    int hours = 0,minute = 0,second = 0;
    String hours_str = String.format("%02d",hours);
    String minute_str = String.format("%02d",minute);
    String second_str = String.format("%02d",second);
    int elapsedTime = 0;
    boolean started = false;


    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime+=1000;
            second = (elapsedTime/1000)%60;
            minute = (elapsedTime/60000)%60;
            hours = (elapsedTime/3600000)%24;
            hours_str = String.format("%02d",hours);
            minute_str = String.format("%02d",minute);
            second_str = String.format("%02d",second);
            lbl.setText(hours_str+" : "+minute_str+" : "+second_str);
        }
    });


    public GUI(){
        l = new DefaultListModel<>();


        list = new JList<>(l);
        list.setBounds(65,210,270,100);


        checkBtn = new JButton("C");
        checkBtn.setBounds(65,150,50,50);
        checkBtn.addActionListener(this);

        startBtn = new JButton("START");
        startBtn.setBounds(115,150,110,50);
        startBtn.addActionListener(this);

        resetBtn = new JButton("RESET");
        resetBtn.setBounds(225,150,110,50);
        resetBtn.addActionListener(this);



        lbl = new JLabel();
        lbl.setText(hours_str+" : "+minute_str+" : "+second_str);
        lbl.setSize(220,100);
        lbl.setOpaque(true);
        lbl.setLocation(90,50);
        lbl.setHorizontalAlignment(JTextField.CENTER);
        lbl.setFont(new Font("Verdana",Font.PLAIN,35));

        add(list);
        add(checkBtn);
        add(startBtn);
        add(lbl);
        add(resetBtn);
        setSize(400,400);
        setTitle("Stopwatch");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = ((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-400)/2;
        int y =((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-400)/2;
        setLocation(x,y);
        setVisible(true);
    }




    public void Start(){
        timer.start(); // burdaki start farklı bir fonksiyondur
    }
    public void reset(){
        elapsedTime = 0;
        second = 0;
        minute = 0;
        hours = 0;
        hours_str = String.format("%02d",hours);
        minute_str = String.format("%02d",minute);
        second_str = String.format("%02d",second);
        lbl.setText(hours_str+" : "+minute_str+" : "+second_str);
        timer.stop();


    }
    public void Stop(){
        timer.stop();

    }
    public void check(String st1,String st2,String st3){
        l.addElement(st1+" : "+st2+" : "+st3);

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn){
            if (started == false){
                started = true;
                Start();
                startBtn.setText("STOP");
            }else{
                Stop();
                started = false;
                startBtn.setText("START");

            }
        }
        if (e.getSource() == resetBtn){
            reset();
            startBtn.setText("START");
            l.clear();

        }

        if (e.getSource() == checkBtn){
            check(hours_str,minute_str,second_str);
        }

    }
}
