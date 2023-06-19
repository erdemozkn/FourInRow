
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Game extends Canvas implements MouseListener {

    private String message = "RED FIRST";

    private int startX = 100, startY = 50, squareSize = 100;
    private int[][] board = new int[6][7];
    private int playerTurn = 1;
    private boolean gameOver;

    public Game() {
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        for (int i = 0; i <= 6; i++) {
            g.drawLine(startX, startY + i * squareSize, startX + 7 * squareSize, startY + i * squareSize);
            for (int j = 0; j <= 7; j++) {
                g.drawLine(startX + j * squareSize, startY, startX + j * squareSize, startY + 6 * squareSize);
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 1) {
                    g.setColor(new Color(255,102,102));
                    g.drawOval(startX + j * squareSize, startY + i * squareSize, 100, 100);
                    g.fillOval(startX + j * squareSize, startY + i * squareSize, 100, 100);
                } else if (board[i][j] == 2) {
                    g.setColor(new Color(51,153,255));
                    g.drawOval(startX + j * squareSize, startY + i * squareSize, 100, 100);
                    g.fillOval(startX + j * squareSize, startY + i * squareSize, 100, 100);
                }
            }
        }
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.RED);
        g.drawString(message, 350, 40);
    }

    public boolean checkForWinner(int player, int[][] grid) {

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player
                        && grid[row][col + 1] == player
                        && grid[row][col + 2] == player
                        && grid[row][col + 3] == player) {
                    return true;
                }
            }
        }
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == player
                        && grid[row + 1][col] == player
                        && grid[row + 2][col] == player
                        && grid[row + 3][col] == player) {
                    return true;
                }
            }
        }
        for (int row = 3; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player
                        && grid[row - 1][col + 1] == player
                        && grid[row - 2][col + 2] == player
                        && grid[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player
                        && grid[row + 1][col + 1] == player
                        && grid[row + 2][col + 2] == player
                        && grid[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    public void mouseClicked(MouseEvent evt) {
        if (!gameOver) {
            if (evt.getX() > startX
                    && evt.getX() < startX + 7 * squareSize
                    && evt.getY() > startY
                    && evt.getY() < startY + 6 * squareSize) {
                int row = (evt.getY() - startY) / squareSize;
                int col = (evt.getX() - startX) / squareSize;
                for (int i = 5; i >= 0; i--) {
                    if (board[i][col] == 0) {
                        board[i][col] = playerTurn;
                        if (checkForWinner(playerTurn, board)) {
                            if (playerTurn == 1) {
                                JOptionPane.showMessageDialog(this,
                  "<html><h1>RED WIN!!!!</h1></html>\n",
                  "WİNNNERRRR!!!",
                  JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(Game.class.getResource("photo/winner.jpg")));
                            } else {
                                JOptionPane.showMessageDialog(this,
                  "<html><h1>BLUE WIN!!!!</h1></html>\n",
                  "WİNNNERRRR!!!",
                  JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(Game.class.getResource("photo/winner.jpg")));
                            }
                            gameOver = true;
                        }
                        

                        if (playerTurn == 1) {
                            message = "BLUE TURN";
                            playerTurn = 2;
                        } 
                        else {
                            message = "RED TURN";
                            playerTurn = 1;
                        }

                        break;
                    }
                }
            }
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}
