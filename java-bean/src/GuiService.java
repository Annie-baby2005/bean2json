

import javax.swing.*;
import java.awt.*;


/**
 * @author lvfushun
 */
public class GuiService extends JFrame {
    private JTextArea textArea;

    public GuiService() {
        try {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/HSI.jpg"));
            this.setIconImage(imageIcon.getImage());
            this.setTitle("JavaBean文本转Json文本（by lfs）");
            this.setSize(1024, 800);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            this.setAlwaysOnTop(!this.isAlwaysOnTop());                 //总在最上

            this.setLocationRelativeTo(null);                           //关闭资源

            JToolBar toolBar = new JToolBar();
            this.add(toolBar, BorderLayout.NORTH);

            JButton but = new JButton("切换置顶");
            but.setFont(new Font("黑体",Font.ROMAN_BASELINE,12));
            Dimension preferredSize = new Dimension(100,20);//设置尺寸
            but.setPreferredSize(preferredSize );
            but.addActionListener(e -> {
                this.setAlwaysOnTop(!this.isAlwaysOnTop());
            });

            JButton button = new JButton("  点击转换为Json  ");
            button.setFont(new Font("黑体",Font.ROMAN_BASELINE,12));
            button.addActionListener(e -> {
                String text = textArea.getText();
                TransformServiceImpl transformService = new TransformServiceImpl();
                String json = transformService.transformJavaBean(text);
                textArea.setText(json);
            });

            JButton butt = new JButton("  点击转换为文档  ");
            butt.setFont(new Font("黑体",Font.ROMAN_BASELINE,12));
            butt.addActionListener(e -> {
                String text = textArea.getText();
                TransformServiceImpl transformService = new TransformServiceImpl();
                String json = transformService.transformToDoc(text);
                textArea.setText(json);
            });

            toolBar.add(but);
            toolBar.add(button);
            toolBar.add(butt);


            textArea = new JTextArea();
            textArea.setFont(new Font("宋体",Font.ROMAN_BASELINE,16));
            this.add(textArea, BorderLayout.CENTER);
            //滚动条
            JScrollPane jsp = new JScrollPane(textArea);
            jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jsp.setBounds(130, 10, 300, 340);
            this.add(jsp);


        }catch (Exception e){

        }


    }

}
