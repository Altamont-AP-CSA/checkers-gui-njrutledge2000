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


        //Fill the board with circle pieces. Use the 2D array of blackPieces and redPieces
        //to set where the pieces are.


        addMouseListener(this);
    }

    public void checkForJump(boolean red){
        //This method will take the boolean input 'red' and will determine if a red piece or black piece jumped another piece.
        //If it did jump, then the 2D array of the opposite color needs to be updated to show one less piece.
    }

    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 200, 150);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(100, 100, 400, 400);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g.drawString("Red's Turn", 50, 50);

        //Below is the algorithm to change turns
        if(lastClick[0] != -1){
            if(redTurn) {
                redPieces[lastClick[1]][lastClick[0]] = true;
                redPieces[firstClick[1]][firstClick[0]] = false;
            }
            else{
                blackPieces[lastClick[1]][lastClick[0]] = true;
                blackPieces[firstClick[1]][firstClick[0]] = false;
            }
            firstClick[0] = -1;
            firstClick[1] = -1;
            redTurn = !redTurn;

        }

        //Write the algorithm using nested loops to clear the board for the squares.
        //You will need 2 different set of nested loops.


        //Write the algorithm using nested loops with an if statement on the inside
        //to set all of the red and black pieces where they belong.



        //Below you need to write a series of if statements that will tell the user
        //what color piece has been clicked on. Run the .jar file to get an idea
        //of what it should look like.

        if(!redTurn){
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, 200, 100);
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Black's Turn", 50, 50);
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
