import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class CalculateFrame extends JFrame{
    private Container cp;
    private JTextField jtf=new JTextField();
    private JPanel jpn=new JPanel(new GridLayout(3,3));
    private JButton jbtn[]=new JButton[9];
    public LoginFrame loginFrame=new LoginFrame();
    private Random rdm=new Random();
    private int randomNum[] = new int[9];//亂數若重複_比對用
    boolean flag=true;
    public CalculateFrame(LoginFrame login){
        loginFrame=login;
        init();
    }
    private void init(){
        cp=this.getContentPane();
        cp.setLayout(new BorderLayout(5,5));
        this.setBounds(300,300,500,600);
        this.setLocationRelativeTo(null);
        cp.add(jtf,BorderLayout.NORTH);
        cp.add(jpn,BorderLayout.CENTER);
        jtf.setHorizontalAlignment(jtf.RIGHT);
        for (int i = 0; i < 9; i++) {
            if(i==0){
                randomNum[i]=rdm.nextInt(9);//第一個亂數產生不需要比對
            }
            else {
                while(flag){
                    randomNum[i]=rdm.nextInt(9);
                    for(int j=0;j<i;j++){
                        if(randomNum[j]==randomNum[i]){
                            flag=true;
                            break;
                        }
                        flag=false;
                    }
                }
            }
            flag=true;
            jbtn[i] = new JButton(Integer.toString(randomNum[i]));
            jpn.add(jbtn[i]);
        }
        for(int i=0;i<jbtn.length;i++){
            jbtn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tempButton=(JButton) e.getSource();
                    jtf.setText(jtf.getText()+tempButton.getText());
                }
            });
        }
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginFrame.setVisible(true);
            }
        });

    }


}
