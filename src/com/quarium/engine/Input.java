package com.quarium.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
  private GameContainer gc;

  private static final int NUM_KEYS = 256;
  private boolean[] keys = new boolean[NUM_KEYS];
  private boolean[] keysLast = new boolean[NUM_KEYS];

  private static final int NUM_BUTTONS = 5;
  private boolean[] buttons = new boolean[NUM_BUTTONS];
  private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

  private int mouseX;
  private int mouseY;
  private int scroll;

  /**
   * Handle incoming inputs from game container.
   * @param gc Game container containing the game.
   */
  public Input(GameContainer gc) {
    this.gc = gc;
    mouseX = 0;
    mouseY = 0;
    scroll = 0;

    gc.getWindow().getCanvas().addKeyListener(this);
    gc.getWindow().getCanvas().addMouseListener(this);
    gc.getWindow().getCanvas().addMouseMotionListener(this);
    gc.getWindow().getCanvas().addMouseWheelListener(this);
  }

  /**
   * Update keys and mouse button state from last frame.
   */
  public void update() {
    scroll = 0;

    for (int i = 0; i < NUM_KEYS; i++) {
      keysLast[i] = keys[i];
    }

    for (int i = 0; i < NUM_BUTTONS; i++) {
      buttonsLast[i] = buttons[i];
    }
  }

  /**
   * Return key status based on keyCode.
   * @param keyCode pressed key on keyboard.
   * @return status of keyboard.
   */
  public boolean isKey(int keyCode) {
    return keys[keyCode];
  }

  /**
   * Check if the key was pressed in last frame but not in current frame.
   * @param keyCode key on keyboard
   * @return true if key is up, false if not.
   */
  public boolean isKeyUp(int keyCode) {
    return !keys[keyCode] && keysLast[keyCode];
  }

  /**
   * Check if the key is pressed at current frame but not in last frame.
   * @param keyCode key on keyboard.
   * @return true if key is down, false if not.
   */
  public boolean isKeyDown(int keyCode) {
    return keys[keyCode] && !keysLast[keyCode];
  }

  /**
   * Check if mouse button is pressed.
   * @param buttonCode button on mouse.
   * @return status of mouse.
   */
  public boolean isButton(int buttonCode) {
    return buttons[buttonCode];
  }

  /**
   * Check if mouse was pressed last frame but not in current frame.
   * @param buttonCode button on mouse.
   * @return true if mouse is up, false if not.
   */
  public boolean isButtonUp(int buttonCode) {
    return !buttons[buttonCode] && buttonsLast[buttonCode];
  }

  /**
   * Check if mouse is pressed this frame but not in last frame.
   * @param buttonCode button on mouse.
   * @return true if mouse is down, false if not.
   */
  public boolean isButtonDown(int buttonCode) {
    return buttons[buttonCode] && !buttonsLast[buttonCode];
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    keys[e.getKeyCode()] = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keys[e.getKeyCode()] = false;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {
    buttons[e.getButton()] = true;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    buttons[e.getButton()] = false;
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    mouseX = (int)(e.getX() / gc.getScale());
    mouseY = (int)(e.getY() / gc.getScale());
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    mouseX = (int)(e.getX() / gc.getScale());
    mouseY = (int)(e.getY() / gc.getScale());
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    scroll = e.getWheelRotation();
  }

  public int getMouseX() {
    return mouseX;
  }

  public int getMouseY() {
    return mouseY;
  }

  public int getScroll() {
    return scroll;
  }
}
