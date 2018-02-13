import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class checkers extends JPanel implements MouseListener{

    boolean[][] blackPieces, redPieces;
    int[] firstClick, lastClick;
    boolean redTurn, redClicked, blackClicked;

    public checkers(){
        //Initialize all of your PIVs in the constructor. 'redTurn' needs to be true, 'firstClick' and 'lastClick' needs to be -1.
        redTurn=true;
        firstClick = new int[2];
        firstClick[0]=-1;firstClick[1]=-1;
        lastClick = new int[2];
        lastClick[0]=-1;lastClick[1]=-1;
        //Fill the board with circle pieces. Use the 2D array of blackPieces and redPieces
        //to set where the pieces are.
        blackPieces = new boolean[8][8];
        redPieces = new boolean[8][8];
        for(int r=0;r<3;r++){
            for(int c=0;c<8;c+=2){
                if(r%2==0){
                    blackPieces[r][c+1]=true;
                }else{
                    blackPieces[r][c]=true;
                }
            }
        }
        for(int r=5;r<8;r++){
            for(int c=0;c<8;c+=2){
                if(r%2==0){
                    redPieces[r][c+1]=true;
                }else{
                    redPieces[r][c]=true;
                }
            }
        }

        addMouseListener(this);
    }

    public void checkForJump(boolean red){
        //This method will take the boolean input 'red' and will determine if a red piece or black piece jumped another piece.
        //If it did jump, then the 2D array of the opposite color needs to be updated to show one less piece.
        if(red){
            if(blackPieces[(firstClick[1]+lastClick[1])/2][(firstClick[0]+lastClick[0])/2]){
                if(Math.abs(lastClick[0]-firstClick[0])==2 && (firstClick[1]-lastClick[1])==2) {
                    blackPieces[(firstClick[1] + lastClick[1]) / 2][(firstClick[0] + lastClick[0]) / 2] = false;
                }
            }
        }else{
            if(redPieces[(firstClick[1]+lastClick[1])/2][(firstClick[0]+lastClick[0])/2]){
                if(Math.abs(lastClick[0]-firstClick[0])==2 && (lastClick[1]-firstClick[1])==2) {
                    redPieces[(firstClick[1] + lastClick[1]) / 2][(firstClick[0] + lastClick[0]) / 2] = false;
                }
            }
        }
    }

    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(100, 100, 400, 400);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g.drawString("Red's Turn", 50, 50);

        //Below is the algorithm to change turns
        if(lastClick[0] != -1){
            if(redTurn) {
                redPieces[lastClick[1]][lastClick[0]] = true;
                redPieces[firstClick[1]][firstClick[0]] = false;
                    checkForJump(redTurn);
                }
            else{
                blackPieces[lastClick[1]][lastClick[0]] = true;
                blackPieces[firstClick[1]][firstClick[0]] = false;
                checkForJump(redTurn);
            }
            firstClick[0] = -1;
            firstClick[1] = -1;
            redTurn = !redTurn;

        }

        //Write the algorithm using nested loops to clear the board for the squares.
        //You will need 2 different set of nested loops.
        for(int r=0;r<8;r++){
            for(int c=0;c<8;c++){
                if(r%2==c%2){
                    g.clearRect(c*50+100,r*50+100,50,50);
                }
            }
        }

        //Write the algorithm using nested loops with an if statement on the inside
        //to set all of the red and black pieces where they belong.
        for(int r=0;r<8;r++){
            for(int c=0;c<8;c++){
                if(redPieces[r][c]){
                    g.setColor(Color.RED);
                    g.fillOval(c*50+100,r*50+100,50,50);
                }
                if(blackPieces[r][c]){
                    g.setColor(Color.BLACK);
                    g.fillOval(c*50+100,r*50+100,50,50);
                }
            }
        }


        //Below you need to write a series of if statements that will tell the user
        //what color piece has been clicked on. Run the .jar file to get an idea
        //of what it should look like.

        if(!redTurn){
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, 200, 100);
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Black's Turn", 50, 50);
        }
        if(firstClick[0]!=-1 && firstClick[1]!=-1) {
            if (blackPieces[firstClick[1]][firstClick[0]]) {
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Black Clicked", 50, 550);
            }
            if (redPieces[firstClick[1]][firstClick[0]]) {
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Red Clicked", 50, 550);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        //This method will need to be updated to show which color piece was clicked on.


        int x = e.getX();
        int y = e.getY();
        System.out.println(x + ", " + y);

        System.out.println("Mouse Clicked!");
        x = (x - 100)/50;
        y = (y - 100)/50;
        if(firstClick[0] == -1){
            firstClick[0] = x;
            firstClick[1] = y;
            lastClick[0] = -1;
            lastClick[1] = -1;
            if(redPieces[firstClick[1]][firstClick[0]]){
                System.out.println("Red Clicked!");
            }else if(blackPieces[firstClick[1]][firstClick[0]]) {
                System.out.println("black Clicked!");
            }
        }

        else if(firstClick[0] != -1){
            lastClick[0] = x;
            lastClick[1] = y;
        }
        repaint();

    }

    @Override
    public void mousePressed(MouseEvent e){ }
    @Override
    public void mouseReleased(MouseEvent e){ }
    @Override
    public void mouseEntered(MouseEvent e){ }
    @Override
    public void mouseExited(MouseEvent e){ }


    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.getContentPane().add(new checkers());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
