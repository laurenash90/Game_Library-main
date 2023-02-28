import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class TicTacToeEngine implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    JMenuBar menuBar;
    JMenu exitMenu;
    JMenu playMenu;
    JMenuItem library;
    JMenuItem playAgain;

    /**
     * Constructor for Tic-Tac-Toe
     */
    TicTacToeEngine(){
        menuBar = new JMenuBar();

        playMenu = new JMenu("Play Again");

        playAgain = new JMenuItem("Play Again");




        playMenu.add(playAgain);

        menuBar.add(playMenu);
        frame.setJMenuBar(menuBar);

        playAgain.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textField);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();

    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == playAgain){
            frame.dispose();
            new TicTacToeEngine();
        }

        for(int i = 0; i < 9; i++){
            if(e.getSource() == buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText().equals("")){
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        check();
                    }
                }else{
                    if(buttons[i].getText().equals("")){
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("O");
                        player1_turn = true;
                        textField.setText("X turn");
                        check();
                    }

                }
            }
        }
    }

    /**
     * Sets the first turn randomly
     */
    public void firstTurn(){

        if(random.nextInt(2) == 0){
            player1_turn = true;
            textField.setText("X turn");
        }else{
            player1_turn = false;
            textField.setText("O turn");
        }

    }

    /**
     * Checks the board for a winner
     */

    public void check(){
        //check X
        if(     (buttons[0].getText().equals("X")) &&
                (buttons[1].getText().equals("X")) &&
                (buttons[2].getText().equals("X"))){
            xWins(0,1,2);
        }
        else if(     (buttons[3].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[5].getText().equals("X"))){
            xWins(3,4,5);
        }
        else if(     (buttons[6].getText().equals("X")) &&
                (buttons[7].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))){
            xWins(6,7,8);
        }
        else if(     (buttons[0].getText().equals("X")) &&
                (buttons[3].getText().equals("X")) &&
                (buttons[6].getText().equals("X"))){
            xWins(0,3,6);
        }
        else if(     (buttons[1].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[7].getText().equals("X"))){
            xWins(1,4,7);
        }
        else if(     (buttons[2].getText().equals("X")) &&
                (buttons[5].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))){
            xWins(2,5,8);
        }
        else if(     (buttons[0].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))){
            xWins(0,4,8);
        }
        else if(     (buttons[2].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[6].getText().equals("X"))){
            xWins(2,4,6);
        }

        //check 0
        if(     (buttons[0].getText().equals("O")) &&
                (buttons[1].getText().equals("O")) &&
                (buttons[2].getText().equals("O"))){
            oWins(0,1,2);
        }
        else if(     (buttons[3].getText().equals("O")) &&
                (buttons[4].getText().equals("O")) &&
                (buttons[5].getText().equals("O"))){
            oWins(3,4,5);
        }
        else if(     (buttons[6].getText().equals("O")) &&
                (buttons[7].getText().equals("O")) &&
                (buttons[8].getText().equals("O"))){
            oWins(6,7,8);
        }
        else if(     (buttons[0].getText().equals("O")) &&
                (buttons[3].getText().equals("O")) &&
                (buttons[6].getText().equals("O"))){
            oWins(0,3,6);
        }
        else if(     (buttons[1].getText().equals("O")) &&
                (buttons[4].getText().equals("O")) &&
                (buttons[7].getText().equals("O"))){
            oWins(1,4,7);
        }
        else if(     (buttons[2].getText().equals("O")) &&
                (buttons[5].getText().equals("O")) &&
                (buttons[8].getText().equals("O"))){
            oWins(2,5,8);
        }
        else if(     (buttons[0].getText().equals("O")) &&
                (buttons[4].getText().equals("O")) &&
                (buttons[8].getText().equals("O"))){
            oWins(0,4,8);
        }
        else if(     (buttons[2].getText().equals("O")) &&
                (buttons[4].getText().equals("O")) &&
                (buttons[6].getText().equals("O"))){
            oWins(2,4,6);
        }

    }

    /**
     *
     * @param a location 1
     * @param b location 2
     * @param c location 3
     *          If all locations are in a line or diag then X wins
     */

    public void xWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);

        buttons[b].setBackground(Color.GREEN);

        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("X WINS!!");

        //JOption Pane for Game Over
        String[] responses = {"Play Again", "Game Library"};
        ImageIcon iconx = new ImageIcon("src//Images//xwins.png");
        int choice = JOptionPane.showOptionDialog(frame,"X Wins!", "Game Over",
            JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, iconx, responses, 0);
        //Play Again
        if (choice == JOptionPane.YES_OPTION) {
            new TicTacToeEngine();
            frame.dispose();
        }
        //Game Library
        if (choice == JOptionPane.NO_OPTION) {
            new LaunchPage();
            frame.dispose();
        }
    }

    /**
     *
     * @param a location 1
     * @param b location 2
     * @param c location 3
     *          If all locations are in a line or diag then O wins
     */

    public void oWins(int a, int b, int c){

        buttons[a].setBackground(Color.GREEN);

        buttons[b].setBackground(Color.GREEN);

        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("O WINS!!");

        //JOption Pane for Game Over
        String[] responses = {"Play Again", "Game Library"};
        ImageIcon icono = new ImageIcon("src//Images//owins.png");
        int choice = JOptionPane.showOptionDialog(frame,"O Wins!", "Game Over",
            JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icono, responses, 0);
        //Play Again
        if (choice == JOptionPane.YES_OPTION) {
            new TicTacToeEngine();
            frame.dispose();
        }
        //Game Library
        if (choice == JOptionPane.NO_OPTION) {
            new LaunchPage();
            frame.dispose();
        }
    }
}
