

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PockmonGUI extends JPanel {
    MyKeyListener listener = new MyKeyListener();
    RunAwayListener runAwayListener = new RunAwayListener();
    ElectricBallListener electricBallListener = new ElectricBallListener();
    FightDefeatKeyListener fightDefeatKeyListener = new FightDefeatKeyListener();
    BumpListener bumpListener = new BumpListener();
    static int[][] mapArray = {
            {7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 9, 9, 9, 9, 7, 7, 7, 7, 7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 3},
            {8, 11, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 9, 9, 9, 9, 7, 2, 2, 2, 2, 3, 0, 3, 3, 3, 3, 3, 6, 6, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 3},
            {7, 2, 7, 7, 7, 3, 3, 3, 3, 3, 7, 7, 9, 2, 2, 2, 7, 2, 7, 7, 2, 3, 0, 0, 0, 0, 0, 0, 6, 6, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 3},
            {7, 2, 2, 7, 7, 7, 3, 3, 3, 3, 7, 7, 9, 2, 9, 2, 7, 2, 7, 8, 2, 3, 0, 0, 0, 0, 0, 1, 0, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 12, 12, 12, 12, 12, 15, 12, 12, 3},
            {7, 7, 2, 7, 7, 7, 3, 3, 3, 3, 7, 7, 9, 2, 9, 2, 7, 2, 7, 2, 2, 3, 0, 0, 0, 0, 0, 1, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 12, 12, 12, 12, 12, 12, 3, 3},
            {7, 7, 2, 7, 7, 7, 3, 3, 3, 3, 7, 7, 9, 2, 9, 2, 7, 2, 7, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 10, 0, 9, 9, 9, 12, 12, 12, 12, 3},
            {7, 7, 2, 2, 10, 10, 10, 0, 0, 0, 10, 10, 10, 2, 9, 2, 7, 2, 7, 2, 2, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 10, 0, 9, 9, 9, 12, 12, 12, 12, 3},
            {7, 7, 4, 4, 10, 10, 10, 0, 0, 0, 10, 10, 10, 10, 9, 2, 7, 2, 7, 7, 7, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 10, 0, 9, 9, 9, 12, 12, 12, 12, 3},
            {7, 7, 4, 4, 10, 9, 7, 3, 3, 3, 3, 3, 10, 2, 9, 2, 2, 2, 2, 2, 2, 2, 0, 3, 3, 3, 3, 4, 4, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 10, 0, 9, 9, 9, 12, 12, 12, 12, 3},
            {7, 7, 2, 2, 10, 9, 7, 3, 3, 3, 3, 3, 9, 2, 9, 7, 2, 2, 2, 2, 2, 2, 0, 3, 3, 3, 3, 4, 4, 3, 3, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 10, 0, 9, 9, 9, 12, 12, 12, 12, 3},
            {7, 7, 2, 2, 10, 9, 7, 3, 3, 3, 3, 3, 9, 2, 9, 7, 2, 7, 7, 7, 7, 3, 0, 3, 3, 3, 3, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 9, 9, 9, 9, 9, 9, 9, 3},
            {7, 7, 2, 5, 10, 9, 7, 3, 3, 3, 3, 3, 9, 2, 9, 7, 2, 7, 7, 7, 7, 3, 0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 9, 9, 9, 9, 9, 9, 9, 3},
            {7, 7, 10, 10, 10, 9, 7, 3, 3, 3, 3, 3, 9, 2, 9, 7, 2, 7, 7, 7, 7, 3, 0, 3, 3, 3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {7, 7, 10, 10, 10, 9, 7, 3, 3, 3, 3, 3, 9, 2, 9, 7, 2, 7, 7, 7, 7, 3, 0, 3, 3, 3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {7, 7, 10, 10, 10, 9, 7, 3, 3, 3, 3, 3, 9, 2, 9, 7, 2, 7, 7, 7, 7, 3, 0, 3, 3, 3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 3, 3, 0, 0, 9, 9, 9, 9, 9, 0, 3},
            {7, 7, 10, 10, 10, 9, 7, 3, 3, 3, 3, 3, 9, 2, 9, 7, 2, 7, 7, 7, 7, 3, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 9, 13, 13, 13, 13, 0, 3},
            {7, 7, 10, 10, 10, 9, 7, 3, 3, 3, 3, 3, 9, 2, 9, 7, 2, 7, 7, 7, 7, 3, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 9, 13, 13, 13, 13, 0, 3},
            {7, 7, 10, 10, 10, 9, 7, 7, 7, 7, 3, 3, 9, 2, 9, 7, 2, 7, 2, 4, 4, 2, 0, 3, 0, 3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 9, 13, 13, 13, 13, 0, 3},
            {7, 7, 2, 10, 10, 9, 7, 7, 7, 7, 3, 3, 7, 2, 7, 7, 2, 7, 2, 10, 10, 2, 0, 3, 0, 3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 9, 13, 13, 13, 13, 0, 3},
            {7, 7, 2, 10, 10, 9, 7, 7, 7, 7, 3, 3, 7, 2, 7, 7, 2, 7, 2, 2, 2, 2, 0, 3, 0, 3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 9, 13, 13, 13, 13, 0, 3},
            {7, 7, 2, 10, 10, 9, 7, 7, 7, 7, 3, 3, 7, 2, 7, 7, 2, 5, 2, 2, 2, 2, 0, 3, 0, 3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 9, 13, 13, 13, 13, 0, 3},
            {7, 7, 2, 10, 10, 9, 7, 7, 7, 7, 3, 3, 7, 2, 7, 7, 2, 2, 2, 4, 5, 2, 0, 3, 0, 3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 9, 13, 14, 13, 13, 13, 0, 3},
            {7, 7, 11, 10, 10, 9, 7, 7, 7, 7, 3, 3, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 9, 13, 13, 13, 13, 0, 3},
            {3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 2, 2, 2, 2, 7, 7, 2, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 9, 13, 13, 13, 13, 0, 3},
            {3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 3, 3, 2, 2, 2, 2, 2, 2, 2, 7, 7, 2, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 9, 13, 13, 13, 13, 13, 3},
            {3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 3, 3, 2, 2, 2, 2, 2, 2, 2, 7, 7, 2, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 9, 9, 13, 13, 13, 13, 13, 13, 3},
            {3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 3, 3, 10, 10, 10, 2, 2, 2, 2, 7, 7, 2, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 13, 13, 13, 14, 13, 13, 13, 3},
            {3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 3, 3, 10, 10, 10, 2, 2, 2, 2, 7, 7, 2, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 13, 13, 13, 13, 13, 13, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 10, 2, 2, 2, 2, 7, 7, 0, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 13, 13, 13, 13, 13, 13, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 10, 2, 2, 2, 2, 7, 7, 2, 2, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 10, 10, 13, 13, 13, 13, 13, 13, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
    };
    static private int ibaoxiang=0;
    static private int iFireMonster=0;
    static private int ibegin = 0;
    static private int italk1 = 0;
    static private int italk2 = 0;
    static private int iForestMonster = 0;
    static private int iwaterMonster=0;
    static private int iPaint = 0;
    static private double movePixel = 32;
    static private double map_x = 16 * 7-3;
    static private double map_y = -16 * 40;
    static private int myx = 12;//初始化角色信息。位于地图中央
    static private int myy = 29;
    static private int lastMoveState = 1;
    static private int moveState = 1;
    static private String myname;
    static private Random r = new Random();
    static private MyMonster ElectricMouse = MyMonster.getInstance();
    static private SimpleAttributeSet attributeSet = new SimpleAttributeSet();
    static private Image electricBallIconGif = Toolkit.getDefaultToolkit().getImage("电球攻击.gif");
    static private Image bumpIconGif = Toolkit.getDefaultToolkit().getImage("冲撞.gif");
    static private Icon bumpIconPng = new ImageIcon("冲撞.png");
    static private Icon endIconPng =new ImageIcon("通关.png");
    static private Icon electricBallIconPng = new ImageIcon("电球攻击.png");
    static private Icon PockmonStaticIcon = new ImageIcon("宝可梦战斗静止.png");
    static private Icon fightDefeatIcon = new ImageIcon("战斗失败.png");
    static private Icon fightVictoryIcon = new ImageIcon("战斗胜利.png");
    static private Icon runAwayIcon = new ImageIcon("逃跑.png");
    static private Icon enterIcon = new ImageIcon("EnterImage.jpg");
    static private Icon goIcon = new ImageIcon("Go.jpg");
    static private Icon mapIcon = new ImageIcon("Map.png");
    static private Icon downStatic = new ImageIcon("朝下走静态.png");
    static private Icon upStatic = new ImageIcon("朝上走静态.png");
    static private Icon leftStatic = new ImageIcon("朝左走静态.png");
    static private Icon rightStatic = new ImageIcon("朝右走静态.png");
    static private Icon down1 = new ImageIcon("朝下走1.png");
    static private Icon up1 = new ImageIcon("朝上走1.png");
    static private Icon left1 = new ImageIcon("朝左走1.png");
    static private Icon right1 = new ImageIcon("朝右走1.png");
    static private Icon down2 = new ImageIcon("朝下走2.png");
    static private Icon up2 = new ImageIcon("朝上走2.png");
    static private Icon left2 = new ImageIcon("朝左走2.png");
    static private Icon right2 = new ImageIcon("朝右走2.png");
    static private Icon sentenceEnd = new ImageIcon("句末.png");
    static private Icon forestFightIcon = new ImageIcon("森林战斗背景.jpg");
    static private Icon waterFightIcon =new ImageIcon("冰面战斗背景.png");
    static private Icon fireFightIcon =new ImageIcon("火山.png");
    static private Icon fireMonsterIcon =new ImageIcon("喷火龙.png");
    static private Icon forestMonsterIcon = new ImageIcon("妙蛙种子.png");
    static private Icon waterMonsterIcon =new ImageIcon("水箭龟.png");
    static private JLabel endLabel= new JLabel(endIconPng);
    static private JLabel fireFightLabel =new JLabel(fireFightIcon);
    static private JLabel fireMonsterLabel=new JLabel(fireMonsterIcon);
    static private JLabel waterMonsterLabel =new JLabel(waterMonsterIcon);
    static private JLabel forestFightLabel = new JLabel(forestFightIcon);
    static private JLabel waterFightLabel =new JLabel(waterFightIcon);
    static private JLabel forestMonsterLabel = new JLabel(forestMonsterIcon);
    static private JLabel PockmonLabel = new JLabel(downStatic);
    static private JLabel mapLabel = new JLabel(mapIcon);
    static private JLabel beginLabel = new JLabel(enterIcon);
    static private JLabel fightDefeatLabel = new JLabel(fightDefeatIcon);
    static private JLabel fightVictoryLabel = new JLabel(fightVictoryIcon);
    static private JLabel PockmonStaticLabel = new JLabel(PockmonStaticIcon);
    /*static private JLabel electricBallLabel =new JLabel(electricBallIconGif);*/
    static private MyJPanel electricBallPanel = new MyJPanel(electricBallIconGif, 159);
    static private MyJPanel bumpPanel = new MyJPanel(bumpIconGif, 231);
    static private JPanel mapAndPlayPanel = new JPanel();
    static private JTextPane talk1Pane = new JTextPane();
    static private JButton electricBallButton = new JButton(electricBallIconPng);
    static private JButton bumpButton = new JButton(bumpIconPng);
    static private JButton beginButton = new JButton(goIcon);
    static private JButton runAwayButton = new JButton(runAwayIcon);
    static private Monster forestMonster1 = new Monster(forestFightLabel, forestMonsterLabel, 100, 100,
            new Skill("冲撞", 8), new Skill("拍打", 10), 3, "妙蛙种子", 1, 1);
    static private Monster forestMonster2 = new Monster(forestFightLabel, forestMonsterLabel, 100, 100,
            new Skill("冲撞", 8), new Skill("拍打", 10), 3, "妙蛙种子", 2, 22);
    static private Document doc = talk1Pane.getStyledDocument();
    static private Monster waterMonster1 = new Monster(waterFightLabel,waterMonsterLabel,200,200,
            new Skill("冲撞", 8),new Skill("水箭攻击",12),5,"水箭龟",47,26);
    static private Monster waterMonster2 = new Monster(waterFightLabel,waterMonsterLabel,200,150,
            new Skill("冲撞", 8),new Skill("水箭攻击",12),5,"水箭龟",46,21);
    static private Monster fireMonster = new Monster(fireFightLabel,fireMonsterLabel,400,400,
            new Skill("火球攻击", 10),new Skill("绝命火焰",14),7,"喷火龙",47,3);
    public static void main(String[] args) throws Exception {
        Music audioPlayWave = new Music("恋色空.wav");// 开音乐 音樂名
        audioPlayWave.start();
        @SuppressWarnings("unused")
        int musicOpenLab = 1;
        talk1Pane.setEditable(false);
        talk1Pane.setRequestFocusEnabled(false);
        JFrame frame = new JFrame("Pokemon");
        frame.setSize(800, 480);
        frame.setLocation(420, 150);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setContentPane(new PockmonGUI());
        Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);
        Image myImage;
        myImage = Toolkit.getDefaultToolkit().createImage("鼠标图标.png");
        Point myPoint = new Point(30, 30);
        myCursor = Toolkit.getDefaultToolkit().createCustomCursor(myImage, myPoint, "我的光标");
        frame.setCursor(myCursor);

    }

    public PockmonGUI() {
        loadEnterImage();
    }

    public void loadEnterImage() {
        this.setLayout(null); //设置绝对布局
        beginLabel.setBounds(0, 0, 800, 480);
        this.add(beginLabel);
        beginButton.setSize(228, 113);
        beginButton.setLocation(313, 150);
        this.add(beginButton);
        beginButton.addActionListener(new GameBeginListener());
    }


    public void beginGame() {
        loadMap();
        //初始化地图
    }

    public void loadMap() {

        mapAndPlayPanel.removeKeyListener(listener);
        this.removeAll();
        if (ibegin == 0) {
            PockmonGUI.this.setLayout(null);
            talk1Pane.setLocation(0, 350);
            talk1Pane.setSize(780, 120);
            talk1Pane.setText("（睁开眼） 阿巴阿巴--\n" +
                    ""+myname+"：“这是哪，我是谁？”（自言自语）；（按F以继续）");
            talk1Pane.insertIcon(sentenceEnd);
            PockmonGUI.this.add(talk1Pane);
            ibegin++;
        }
        this.updateUI();
        mapAndPlayPanel.setLayout(null);
        mapLabel.setBounds((int)map_x, (int)map_y, 1623, 975);
        PockmonLabel.setBounds(505, 297, 26, 26);
        mapAndPlayPanel.add(mapLabel);
        this.setLayout(new BorderLayout());
        PockmonLabel.setOpaque(false);
        mapAndPlayPanel.setOpaque(false);
        this.add(PockmonLabel);
        this.add(mapAndPlayPanel, BorderLayout.CENTER);
        mapAndPlayPanel.setVisible(true);
        mapAndPlayPanel.requestFocus();
        mapAndPlayPanel.addKeyListener(listener);
        this.updateUI();
        System.out.println("添加地图");
//        this.addKeyListener(mapAndPlayPanel);
        System.out.println("设置可视化");
        //mapAndPlayPanel.addKeyListener(new MapListener());//使用地图时开启键盘监听，进入战斗关闭键盘监听
    }


    public void Fight(Monster monster) {
        ElectricMouse.setCurrentBlood(ElectricMouse.getBlood());
        monster.setCurrentBlood(monster.getBlood());
        runAwayButton.removeActionListener(runAwayListener);
        electricBallButton.removeActionListener(electricBallListener);
        mapAndPlayPanel.removeKeyListener(listener);
        bumpButton.removeActionListener(bumpListener);
        this.removeAll();
        this.setLayout(null);
        talk1Pane.setLocation(400, 320);
        talk1Pane.setSize(185, 120);
        PockmonGUI.this.add(talk1Pane);
        talk1Pane.setText("战斗开始\n敌方" + monster.getName() + ":血量" + monster.getBlood() + "；攻击力" + monster.getATK() + "\n" +
                "我方电气鼠:等级：" + ElectricMouse.getLevel() + "；血量" + ElectricMouse.getBlood() + "；攻击力" + ElectricMouse.getATK() + "\n请选择电气鼠的行动");
        talk1Pane.insertIcon(sentenceEnd);
        runAwayButton.setSize(200, 120);
        runAwayButton.setLocation(585, 320);
        this.add(runAwayButton);
        monster.getMonsterLabel().setBounds(480, 110, 250, 250);
        monster.getMonsterLabel().setOpaque(false);
        this.add(monster.getMonsterLabel());
        runAwayButton.addActionListener(runAwayListener);
        //判断是否获得电球技能
        if (ElectricMouse.isGetElectricBall() == true) {
            electricBallButton.setSize(200, 120);
            electricBallButton.setLocation(0, 320);
            this.add(electricBallButton);
            electricBallListener.setMonster(monster);
            if (electricBallButton.getActionListeners() != null)
                electricBallButton.addActionListener(electricBallListener);
        }
        bumpButton.setSize(200, 120);
        bumpButton.setLocation(200, 320);
        this.add(bumpButton);
        bumpListener.setMonster(monster);
        bumpButton.addActionListener(bumpListener);//添加冲撞技能
        PockmonStaticLabel.setOpaque(false);
        PockmonStaticLabel.setBounds(120, 120, 230, 230);
        this.add(PockmonStaticLabel);
        this.updateUI();
        monster.getFightLabel().setBounds(0, 0, 800, 480);
        monster.getFightLabel().setOpaque(false);
        this.add(monster.getFightLabel());
        this.updateUI();
    }


    void fightDefeat() {
        this.removeAll();
        fightDefeatLabel.setBounds(0, 0, 800, 480);
        this.add(fightDefeatLabel);
        this.updateUI();
        this.requestFocus();
        this.removeKeyListener(fightDefeatKeyListener);
        this.addKeyListener(fightDefeatKeyListener);
    }
    void end() {
        this.removeAll();
        endLabel.setBounds(0, 0, 800, 480);
        this.add(endLabel);
        this.updateUI();
    }

    void fightVictory() {
        this.removeAll();
        if(mapArray[3][47]==0){
            end();
            return;
        }
        fightVictoryLabel.setBounds(0, 0, 800, 480);
        this.add(fightVictoryLabel);
        PockmonGUI.this.setLayout(null);
        talk1Pane.setLocation(0, 385);
        talk1Pane.setSize(780, 100);
        PockmonGUI.this.add(talk1Pane);
        talk1Pane.setText("获得经验：50;"+"离下一等级经验:"+(100-ElectricMouse.lookExp())+
                "\n电气鼠现在等级：" + ElectricMouse.getLevel() + "；血量" + ElectricMouse.getBlood() + "；攻击力:" + ElectricMouse.getATK() );
        talk1Pane.insertIcon(sentenceEnd);
        this.updateUI();
        this.requestFocus();
        this.removeKeyListener(fightDefeatKeyListener);
        this.addKeyListener(fightDefeatKeyListener);
    }

    class GameBeginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("游戏开始");
            myname=JOptionPane.showInputDialog(null,"请输入你的游玩名字");
            if(myname==null) myname="无名氏";
            System.out.println(myname);
            beginGame();
        }
    }

    public class MyKeyListener extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 1 == myy && myx == 1 && mapArray[1][0] == 8) {
                ibaoxiang++;
                if (ibaoxiang == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("发现一个宝箱（按F打开宝箱）");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibaoxiang == 2) {
                    talk1Pane.setText("打开宝箱：（什么都没有）”这，玩我呢？（按F退出对话继续探索)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if(ibaoxiang==3) {
                    PockmonGUI.this.remove(talk1Pane);
                    PockmonGUI.this.updateUI();
                    ibaoxiang=0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 4>= myy&&myy>=2 && myx >= 18&&myx<=20 && mapArray[3][19] == 8) {
                ibaoxiang++;
                if (ibaoxiang == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("发现一个宝箱（按F打开宝箱）");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibaoxiang == 2) {
                    talk1Pane.setText("打开宝箱：（一根树枝）”这玩意能当武器吗？（按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibaoxiang == 3) {
                    talk1Pane.setText("获得武器：树枝；攻击力增加3点（按F以退出对话继续探索)");
                    ElectricMouse.setATK(ElectricMouse.getATK()+3);
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if(ibaoxiang==4) {
                    PockmonGUI.this.remove(talk1Pane);
                    PockmonGUI.this.updateUI();
                    ibaoxiang=0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 4>= myy&&myy>=2 && myx >= 28&&myx<=30 && mapArray[3][29] == 8) {
                ibaoxiang++;
                if (ibaoxiang == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("发现一个宝箱（按F打开宝箱）");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibaoxiang == 2) {
                    talk1Pane.setText("打开宝箱：哇库哇库，这宝箱有电，我感觉自己快炸毛了（按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibaoxiang == 3) {
                    talk1Pane.setText("获得技能：电球攻击（按F以退出对话继续探索)");
                    ElectricMouse.getGetElectricBall();
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if(ibaoxiang==4) {
                    PockmonGUI.this.remove(talk1Pane);
                    PockmonGUI.this.updateUI();
                    ibaoxiang=0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 2 == myy && myx == 1 && mapArray[1][1] == 11) {
                iForestMonster++;
                if (iForestMonster == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("”终于找到你了，这就是我回家的第一步“（按F进入战斗）");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (iForestMonster == 2) {
                    Fight(forestMonster1);
                    iForestMonster=0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 21 <= myy&&myy<=23 && myx >= 1&&myx<=3 && mapArray[22][2] == 11) {
                iForestMonster++;
                if (iForestMonster == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("”终于找到你了，这就是我回家的第一步“（按F进入战斗）");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (iForestMonster == 2) {
                    Fight(forestMonster2);
                    iForestMonster=0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 25<= myy&&myy<=27 && myx <=48&&myx>=46 && mapArray[26][47] == 14) {
                iwaterMonster++;
                if (iwaterMonster == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("”就是这个水王八吗，看我的。“（按F进入战斗）");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (iwaterMonster == 2) {
                    Fight(waterMonster1);
                    iwaterMonster=0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 20<= myy&&myy<=22 && myx <=47&&myx>=45 && mapArray[21][46] == 14) {
                iwaterMonster++;
                if (iwaterMonster == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("”就是这个水王八吗，看我的。“（按F进入战斗）");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (iwaterMonster == 2) {
                    Fight(waterMonster2);
                    iwaterMonster=0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 2 <= myy&&myy<=4 && myx >= 46&&myx<=48 && mapArray[3][47] == 15) {
                iFireMonster++;
                if (iFireMonster == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("”这家伙睡得这么香，看我——偷袭！“（按F进入战斗）");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (iFireMonster == 2) {
                    Fight(fireMonster);
                    iFireMonster=0;
                }
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_F && 29 == myy && myx == 12) {
                ibegin++;
                if (ibegin == 2) {
                    try {
                        doc.insertString(doc.getLength(), "\n一个低沉的npc声音：“这是宝可梦的世界，你好像转生成了黄皮耗子。”(按F以继续)", attributeSet);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 3) {

                    talk1Pane.setText("\n"+myname+"：“真的耶，我这小手，香香的，软软的~”(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 4) {
                    try {
                        doc.insertString(doc.getLength(), "\n一个难过的npc声音：“看来你很喜欢你这副模样呢，那你就这样呆在这个世界吧，我这个布置任务的npc好像没有什么存在的意义了呢，呜呜~~”(按F以继续)", attributeSet);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 5) {
                    talk1Pane.setText("\n"+myname+"：“啊嘞，毕竟这可是宝可梦的是世界嘛！没什么不好的，呆在这个世界和和睦睦，有幸还能被可爱的美少女训练师抓获，变成她的狗（bushi）~”（发病ing）(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 6) {
                    talk1Pane.setText("\n一个沙哑的npc声音：“是吗，看来你一点都不了解那群废柴训练师呢，他们抓获了宝可梦只是为了利用你们的技能变成懒狗一条，而且将抓获的宝可梦充分利用后直接放在回复中心不闻不顾，，只想去找新欢呢！！你就只能呆在宝贝球里面再也不能重见天日。”（试图恐吓）(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 7) {
                    talk1Pane.setText("\n"+myname+"：“这~这可不行。\n" +
                            "                我本身就是懒狗一个，还要我服侍别人，牡蛎（做不到）。\n" +
                            "                 爷想回去，回到自己的世界去。”（试探）(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 8) {
                    talk1Pane.setText("\n一个欣喜的npc声音：“这才对嘛，老老实实跟着我们指示去，你就能回去啦！”（终于把任务发布的喜悦）(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 9) {
                    talk1Pane.setText("\n"+myname+"：“果然你们就是想要我这么说呢！”（不耐烦）(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 10) {
                    talk1Pane.setText("\n"+myname+"：“听着，我已经恢复一些记忆了，我哪也不想去，这个世界我不管怎么样，肯定比在原来那个世界好当个废柴好。话说你们这些npc在哪啊，怎么能给我隔空传音的，教教我呗”(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 11) {
                    talk1Pane.setText("\n一个粗犷的npc声音：”那好吧，可是你在这个世界最多只能呆三天呢，而且你在那边已经被报案为失踪了，你也不想你的电脑硬盘被警察和亲人朋友看到吧！“(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 12) {
                    talk1Pane.setText("\n"+myname+"：”请给我回去的方法。“（立即严肃）(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 13) {
                    talk1Pane.setText("\n电音npc：”回去的方法就是击败这座岛上的三只顶级宝可梦，它们分别代表了这座岛上草，水，火三种属性的最强战力：妙蛙花，水箭龟，喷火龙。“(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 14) {
                    talk1Pane.setText("\n"+myname+"：”说干就干，这东西难不倒我。“(按F移除文字面板开始游戏)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (ibegin == 15) {
                    PockmonGUI.this.remove(talk1Pane);
                    PockmonGUI.this.updateUI();
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && (
                    (19 <= myy && myy <= 21 && myx >= 16 && myx <= 18) ||
                            (10 <= myy && myy <= 12 && myx >= 2 && myx <= 4) ||
                            (20 <= myy && myy <= 22 && myx >= 19 && myx <= 21)
            )/*还有其他的判别条件（是否可以触发）*/) {
                //handleFevent();
                italk1++;
                if (italk1 == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("npc1：”瞅我干啥？“（按F以继续）");
                    talk1Pane.insertIcon(sentenceEnd);

                }
                if (italk1 == 2) {
                    talk1Pane.setText(""+myname+"：”？？？和你对话我不应该得到点信息吗？“(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (italk1 == 3) {
                    talk1Pane.setText("npc：”没长眼睛吗，boss在哪地图上清清楚楚，我就一临时当npc的，要道具什么的都没有。“(按F以继续)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (italk1 == 4) {
                    talk1Pane.setText(""+myname+"：”————“(按F移除文字面板继续游戏)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (italk1 == 5) {
                    PockmonGUI.this.remove(talk1Pane);
                    PockmonGUI.this.updateUI();
                    italk1 = 0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F && (
                    (9 <= myy && myy <= 11 && myx >= 28 && myx <= 30) ||
                            (13 <= myy && myy <= 15 && myx >= 37 && myx <= 39)
            )/*还有其他的判别条件（是否可以触发）*/) {
                //handleFevent();
                italk2++;
                if (italk2 == 1) {
                    PockmonGUI.this.setLayout(null);
                    talk1Pane.setLocation(0, 350);
                    talk1Pane.setSize(780, 120);
                    PockmonGUI.this.add(talk1Pane);
                    talk1Pane.setText("npc2；”前面就是——“（按F以继续）");
                    talk1Pane.insertIcon(sentenceEnd);

                }
                if (italk2 == 2) {
                    talk1Pane.setText(""+myname+"：”我知道了，不用你说！“(按F以结束对话继续游戏)");
                    talk1Pane.insertIcon(sentenceEnd);
                }
                if (italk2 == 3) {
                    PockmonGUI.this.remove(talk1Pane);
                    PockmonGUI.this.updateUI();
                    italk2 = 0;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN
                    && (mapArray[myy + 1][myx] == 10 || mapArray[myy + 1][myx] == 2 ||
                    mapArray[myy + 1][myx] == 0 ||
                    mapArray[myy + 1][myx] == 5 ||
                    mapArray[myy + 1][myx] == 12 || mapArray[myy + 1][myx] == 13)/*还有障碍物判断条件*/) {
                System.out.println("朝下移动");
                if (moveState % 2 == 1) {
                    PockmonLabel.setIcon(down1);
                } else PockmonLabel.setIcon(down2);
                PockmonLabel.updateUI();
                moveState++;
                myy += 1;
                map_y -= movePixel;//未完成，还要处理事件，调整地图对应数组。
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_UP && myy != 0
                    && (mapArray[myy - 1][myx] == 10 || mapArray[myy - 1][myx] == 2 || mapArray[myy - 1][myx] == 5 ||
                    mapArray[myy - 1][myx] == 0 || mapArray[myy - 1][myx] == 12
                    || mapArray[myy - 1][myx] == 13)) {
                if (moveState % 2 == 1) {
                    PockmonLabel.setIcon(up1);
                } else PockmonLabel.setIcon(up2);
                PockmonLabel.updateUI();
                moveState++;
                myy -= 1;
                map_y += movePixel;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT
                    && (mapArray[myy][myx - 1] == 10 || mapArray[myy][myx - 1] == 2 || mapArray[myy][myx - 1] == 5 ||
                    mapArray[myy][myx - 1] == 0 | mapArray[myy][myx - 1] == 12
                    || mapArray[myy][myx - 1] == 13)) {
                if (moveState % 2 == 1) {
                    PockmonLabel.setIcon(left1);
                } else PockmonLabel.setIcon(left2);
                PockmonLabel.updateUI();
                moveState++;
                myx -= 1;
                map_x = map_x+movePixel+0.25;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT
                    && (mapArray[myy][myx + 1] == 10 || mapArray[myy][myx + 1] == 2 || mapArray[myy][myx + 1] == 5 ||
                    mapArray[myy][myx + 1] == 0 || mapArray[myy][myx + 1] == 12
                    || mapArray[myy][myx + 1] == 13)) {
                if (moveState % 2 == 1) {
                    PockmonLabel.setIcon(right1);
                } else PockmonLabel.setIcon(right2);
                PockmonLabel.updateUI();
                moveState++;
                myx += 1;
                map_x -= (movePixel+0.25);
            }
            mapLabel.setLocation((int)map_x, (int)map_y);//
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {//朝下走
                PockmonLabel.setIcon(downStatic);
                PockmonLabel.updateUI();
                lastMoveState = 1;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {//朝右走
                PockmonLabel.setIcon(rightStatic);
                PockmonLabel.updateUI();
                lastMoveState = 2;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                PockmonLabel.setIcon(leftStatic);
                PockmonLabel.updateUI();
                lastMoveState = 3;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                PockmonLabel.setIcon(upStatic);
                PockmonLabel.updateUI();
                lastMoveState = 4;
            }
            System.out.println("released");
        }
    }

    class BumpListener implements ActionListener {
        private Monster monster;

        public void setMonster(Monster monster) {
            this.monster = monster;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PockmonGUI.this.remove(electricBallPanel);
            iPaint = 0;//初始化计数
            int myhurt = (8 * ElectricMouse.getATK() + r.nextInt(2 * ElectricMouse.getATK()) - ElectricMouse.getATK());
            monster.setCurrentBlood(monster.getCurrentBlood() - myhurt);
            Skill skill = new Skill();
            int monsterskill = r.nextInt(2);
            if (monsterskill == 0) {
                skill = monster.getSkill1();
            }
            if (monsterskill == 1) {
                skill = monster.getSkill2();
            }
            int monsterhurt = (skill.getPower() * monster.getATK() + r.nextInt(2 * monster.getATK()) - monster.getATK());
            ElectricMouse.setCurrentBlood(ElectricMouse.getCurrentBlood() - monsterhurt);
            talk1Pane.setText("电气鼠使用了冲撞造成了" + myhurt + "点伤害\n" + monster.getName() + "的血量还剩" + monster.getCurrentBlood() + "点\n"
                    + monster.getName() + "使用了" + skill.getSkillName() + "造成了" + monsterhurt + "点伤害\n" + "电气鼠的血量还剩" + ElectricMouse.getCurrentBlood() + "点\n"
                    + "请选择下一步行动");
            PockmonGUI.this.remove(PockmonStaticLabel);
            PockmonGUI.this.remove(monster.getFightLabel());
            PockmonGUI.this.updateUI();
            PockmonGUI.this.setLayout(null);
            bumpPanel.setBounds(120, 120, 230, 230);
            monster.getFightLabel().setOpaque(false);
            bumpPanel.setOpaque(false);
            PockmonGUI.this.add(bumpPanel);
            PockmonGUI.this.add(monster.getFightLabel());
            PockmonGUI.this.updateUI();
            if (monster.getCurrentBlood() <= 0) {
                ElectricMouse.getExp();
                mapArray[monster.getY()][monster.getX()]=0;
                fightVictory();
                return;
            }
            if (ElectricMouse.getCurrentBlood() <= 0) fightDefeat();
        }
    }

    class ElectricBallListener implements ActionListener {
        private Monster monster;

        public void setMonster(Monster monster) {
            this.monster = monster;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PockmonGUI.this.remove(bumpPanel);
            iPaint = 0;//初始化计数
            int myhurt = (12 * ElectricMouse.getATK() + r.nextInt(2 * ElectricMouse.getATK()) - ElectricMouse.getATK());
            monster.setCurrentBlood(monster.getCurrentBlood() - myhurt);
            Skill skill = new Skill();
            int monsterskill = r.nextInt(2);
            if (monsterskill == 0) {
                skill = monster.getSkill1();
            }
            if (monsterskill == 1) {
                skill = monster.getSkill2();
            }
            int monsterhurt = (skill.getPower() * monster.getATK() + r.nextInt(2 * monster.getATK()) - monster.getATK());
            ElectricMouse.setCurrentBlood(ElectricMouse.getCurrentBlood() - monsterhurt);
            talk1Pane.setText("电气鼠使用了电球攻击造成了" + myhurt + "点伤害\n" + monster.getName() + "的血量还剩" + monster.getCurrentBlood() + "点\n"
                    + monster.getName() + "使用了" + skill.getSkillName() + "造成了" + monsterhurt + "点伤害\n" + "电气鼠的血量还剩" + ElectricMouse.getCurrentBlood() + "点\n"
                    + "请选择下一步行动");
            PockmonGUI.this.remove(PockmonStaticLabel);
            PockmonGUI.this.remove(monster.getFightLabel());
            PockmonGUI.this.updateUI();
            PockmonGUI.this.setLayout(null);
            electricBallPanel.setBounds(120, 120, 230, 230);
            monster.getFightLabel().setOpaque(false);
            electricBallPanel.setOpaque(false);
            PockmonGUI.this.add(electricBallPanel);
            PockmonGUI.this.add(monster.getFightLabel());
            PockmonGUI.this.updateUI();
           if (monster.getCurrentBlood() <= 0) {
                ElectricMouse.getExp();
                mapArray[monster.getY()][monster.getX()]=0;
                fightVictory();
                return;
            }
            if (ElectricMouse.getCurrentBlood() <= 0) fightDefeat();
        }
    }

    class RunAwayListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("点击逃跑");
            int res = JOptionPane
                    .showConfirmDialog(null, "你真的要逃跑吗？", "是否逃跑", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                fightDefeat();
                //点击“是”后执行这个代码块
            } else {
                //点击“否”后执行这个代码块
                return;

            }

        }
    }

    public class FightDefeatKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_F) {
                loadMap();
            }
        }
    }

    public static class MyJPanel extends JPanel {
        private Image gif;
        private int time;

        public MyJPanel(Image gif, int time) {
            this.gif = gif;
            this.time = time;
        }

        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            iPaint++;
            if (iPaint == time + 1) {
                PockmonStaticLabel.setBounds(0, 0, 230, 230);
                this.add(PockmonStaticLabel);
            }
            boolean re = super.imageUpdate(img, infoflags, x, y, width, height);
            if (iPaint - time > 0) {
                return false;
            }
            return true;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            System.out.println("绘制第"+iPaint+"次");
            g.drawImage(gif, 0, 0, this);
        }
    }

}
