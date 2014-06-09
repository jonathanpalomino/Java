/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.gui.controles;

/**
 *
 * @author synccon
 */

import java.awt.TextField;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.text.StringCharacterIterator;

public class TrataMascara
  implements Serializable
{
  public static final int texttype = 0;
  public static final int numbtype = 1;
  public static final int datetype = 2;
  public static final int timetype = 3;
  String _mask = "";
  int _maskLength = 0;
  int _datatype = 0;
  int _filterCount = 0;
  int _maskCount = 0;
  int _firstFilterPos = 0;
  int _lastFilterPos = 0;
  int[] _filterPositions;
  int[] _commandCorrections;
  boolean _inRange = true;
  boolean _spaceIgnored = false;
  boolean _includeLiterals = false;

  int _curMaskPos = 0;
  int _nextMaskPos = 0;
  int _curMaskType = 0;
  char _curMaskChar = '\000';
  char _lastMaskChar = '\000';
  int _commandCorrection = 0;
  int _shiftState = 0;
  char _decimalPoint = '.';
  static final int nulltype = 0;
  static final int filtertype = 1;
  static final int literaltype = 2;
  static final int eShiftNul = 0;
  static final int eShiftUpper = 1;
  static final int eShiftLower = 2;
  private boolean debug = false;

  public TrataMascara(String mask, int datatype)
  {
    init();
    setMask(mask);
    setDatatype(datatype);
  }
  public TrataMascara() {
    init();
  }

  public void setMask(String mask)
  {
    if (mask.trim().equals("")) return;

    this._mask = mask;
    this._maskLength = mask.length();

    this._filterCount = 0;
    resetMaskScan();

    for (this._maskCount = 0; getNextMaskChar() != 0; this._maskCount += 1) {
      if (this._curMaskType == 1) {
        this._filterCount += 1;
        this._lastFilterPos = this._curMaskPos;
      }
      if (1 == this._filterCount)
        this._firstFilterPos = this._curMaskPos;
    }
    if (this._maskCount * this._filterCount == 0) {
      return;
    }

    this._filterPositions = new int[this._filterCount];
    this._commandCorrections = new int[this._filterCount];
    resetMaskScan();
    int i = 0; for (int j = 0; i < this._maskCount; i++) {
      getNextMaskChar();
      if (this._curMaskType == 1) {
        this._filterPositions[j] = this._curMaskPos;
        this._commandCorrections[(j++)] = this._commandCorrection;
      }
    }
  }

  public void setDatatype(int datatype) {
    this._datatype = datatype;
  }
  public String getMask() { return this._mask; } 
  public int getDatatype() { return this._datatype; }

  public boolean isSpaceIgnored()
  {
    return this._spaceIgnored;
  }

  public void setSpaceIgnored(boolean spaceIgnored)
  {
    this._spaceIgnored = spaceIgnored;
  }

  public boolean isIncludeLiterals()
  {
    return this._includeLiterals;
  }

  public void setIncludeLiterals(boolean includeLiterals)
  {
    this._includeLiterals = includeLiterals;
  }

  public int initDisplay(String data, StringBuffer newData) {
    if (this._filterCount == 0) {
      newData.append(data);
      return 0;
    }

    int datalen = data.length();
    int inpos = 0;
    boolean zerofill = false;
    boolean dataRightOfDecimal = false;
    boolean maskRightOfDecimal = false;
    resetMaskScan();
    char c;
    for (int i = 0; (c = getNextMaskChar()) != 0; i++)
    {
      //char c;
      if (this._curMaskType == 1) {
        char z = zerofill ? '0' : '_';
        if (inpos < datalen) {
          c = data.charAt(inpos++);
          if (1 == this._datatype)
            if (this._decimalPoint == c) {
              c = z;
              dataRightOfDecimal = true;
              if ((maskRightOfDecimal) && (inpos < datalen))
                c = data.charAt(inpos++);
            } else if ((dataRightOfDecimal) && (!maskRightOfDecimal)) {
              c = z;
              inpos--;
            } else if ((!dataRightOfDecimal) && (maskRightOfDecimal)) {
              c = z;
              while (inpos < datalen)
                if (this._decimalPoint == data.charAt(inpos++)) {
                  if (inpos < datalen)
                    c = data.charAt(inpos++);
                  dataRightOfDecimal = true;
                  break;
                }
            }
        }
        else {
          c = z;
        }
      } else if ((this._curMaskChar == this._decimalPoint) && (1 == this._datatype)) {
        maskRightOfDecimal = true;
        if (datalen == 0) {
          zerofill = true;
          newData.setCharAt(newData.length() - 1, '0');
        }
        else if ((inpos < datalen) && (data.charAt(inpos) == this._decimalPoint)) {
          inpos++;
          dataRightOfDecimal = true;
        }
      }
      newData.append(c);
    }

    if ((1 == this._datatype) && (datalen == 0) && (!zerofill) && (this._maskLength > 0)) {
      newData.setCharAt(newData.length() - 1, '0');
    }
    return this._filterPositions[0] - this._commandCorrections[0];
  }

  public int processKey(KeyEvent e, int pos, String data, StringBuffer newData, int selStart, int selEnd)
  {
    newData.append(data);
    int keyCode = e.getKeyCode();
    if ((!setMaskPos(pos)) && (!isNavKey(keyCode)))
      return -1;
    char key = e.getKeyChar();
    if ((key == this._decimalPoint) && (this._datatype == 1))
    {
      while (getNextMaskChar() != 0)
        if (this._curMaskChar == this._decimalPoint)
          return nextFilterPos();
      return pos;
    }if (1 != this._curMaskType) {
      if ((pos == 0) && (selStart == 0) && (selEnd > this._firstFilterPos)) {
        setMaskPos(pos = this._firstFilterPos);
      } else if (!isNavKey(keyCode)) {
        if (pos > this._lastFilterPos)
          return -1;
        nextFilterPos();
        pos = this._curMaskPos - this._commandCorrection;
      }
    }
    switch (keyCode) {
    case 8:
      if ((selStart < selEnd) && (
        (pos != selStart) || (selEnd - selStart > 1))) {
        clearSelectedText(selStart, selEnd, newData);
        return Math.max(selStart, this._firstFilterPos);
      }

      int newPos = prevFilterPos();
      if ((pos > 0) && (newPos != -1))
        newData.setCharAt(newPos, '_');
      return newPos != -1 ? newPos : pos;
    case 127:
      if (selStart < selEnd) {
        clearSelectedText(selStart, selEnd, newData);
        return Math.max(selStart, this._firstFilterPos);
      }
      newData.setCharAt(pos, '_');
      return pos;
    case 37:
      return prevFilterPos();
    case 39:
      return nextFilterPos();
    case 36:
      return this._filterPositions[0] - this._commandCorrections[0];
    case 35:
      return this._filterPositions[(this._filterCount - 1)] - this._commandCorrections[(this._filterCount - 1)];
    }
    if (matchFilter(key))
    {
      clearSelectedText(selStart, selEnd, newData);
      if (2 == this._shiftState)
        key = Character.toLowerCase(key);
      else if (1 == this._shiftState)
        key = Character.toUpperCase(key);
      newData.setCharAt(pos, key);
      int newpos = nextFilterPos();
      return newpos == -1 ? (lastFilterPosition() + 1) * -1 - 3 : newpos;
    }
    return -2;
  }

  public boolean stripMask(String data, StringBuffer newData) {
    if (this._maskLength == 0) {
      newData.append(data);
      return true;
    }
    resetMaskScan();
    boolean retval = true;
    for (int i = 0; getNextMaskChar() != 0; i++) {
      if (i > data.length() - 1) {
        retval = false;
        break;
      }
      char c = data.charAt(i);
      if (this._curMaskType == 1) {
        if (c != '_') {
          if ((this._spaceIgnored) && (c == ' '))
            continue;
          newData.append(c);
        }
        else if (isMandatory()) {
          retval = false;
        }
      } else if ((this._curMaskChar == this._decimalPoint) && 
        (this._datatype == 1))
      {
        newData.append(this._decimalPoint); } else {
        if ((this._curMaskType != 2) || (!this._includeLiterals))
          continue;
        newData.append(c);
      }
    }
    return retval;
  }

  public boolean isHandledKey(KeyEvent e) {
    if (this._maskLength == 0)
      return false;
    char c = e.getKeyChar();
    if (Character.isISOControl(c))
    {
      int k = e.getKeyCode();
      switch (k) {
      case 35:
      case 36:
      case 37:
      case 39:
        return !e.isShiftDown();
      case 38:
      }switch (c) {
      case '\b':
      case '':
        return true;
      }
      return false;
    }

    return true;
  }

  public String cut(String data, int selStart, int selEnd, StringBuffer newData) {
    newData.append(data);
    setMaskPos(selStart);
    clearSelectedText(selStart, selEnd, newData);
    return data.substring(selStart, selEnd);
  }

  public int paste(String data, String pasteData, int pos, StringBuffer newData, int selStart, int selEnd) {
    boolean hasSelection = selStart < selEnd;
    if (hasSelection)
      pos = selStart;
    if (!setMaskPos(pos))
      return -1;
    StringBuffer sb = new StringBuffer(data);
    int len = pasteData.length();
    KeyEvent e = new KeyEvent(new TextField(), 0, 0L, 0, 0);
    String s = new String(sb.toString());
    int newpos = pos;
    for (int i = 0; i < len; i++) {
      char c = pasteData.charAt(i);
      e.setKeyChar(c);
      e.setKeyCode(c);
      sb = new StringBuffer();
      int selstart = 0; int selend = 0;
      if ((hasSelection) && (newpos >= selStart) && (newpos < selEnd)) {
        if (i == 0) {
          selstart = selStart;
          selend = selEnd;
        }
        else {
          selstart = newpos;
          selend = newpos + 1;
        }
      }
      newpos = processKey(e, newpos, s, sb, selstart, selend);
      if ((newpos == -1) || (newpos == -2)) {
        break;
      }
      s = sb.toString();
    }
    newData.append(s);
    return newpos;
  }

  public String getMatchedText(String t)
  {
    StringBuffer retString = new StringBuffer();

    if ((t != null) && (t.length() > 0))
    {
      String mask = getMask();
      if (mask.length() > 0) {
        int textCharCounter = 0;
        StringCharacterIterator maskIterator = new StringCharacterIterator(mask);
        do
        {
          char textChar = '_';
          char maskChar = maskIterator.current();

          if (textCharCounter < t.length())
          {
            textChar = t.charAt(textCharCounter);
            if (isFilter(maskChar))
            {
              if (matchFilter(textChar, maskChar)) {
                retString.append(textChar);
              }
              else
              {
                retString.append('_');
              }

            }
            else if (isCommand(maskChar)) {
              maskIterator.next();
              if (maskChar == '>')
              {
                if (matchFilter(textChar, maskIterator.current())) {
                  retString.append(Character.toUpperCase(textChar));
                }
                else
                {
                  retString.append('_');
                }
              }
              else if (maskChar == '<')
              {
                if (matchFilter(textChar, maskIterator.current())) {
                  retString.append(Character.toLowerCase(textChar));
                }
                else
                {
                  retString.append('_');
                }
              }
              else
              {
                retString.append(maskIterator.current());
              }

            }
            else
            {
              retString.append(maskChar);
            }
          }
          else
          {
            retString.append('_');
          }

          textCharCounter++;
        }
        while (maskIterator.next() != 65535);
      }
      else
      {
        retString.append(t);
      }

    }

    return retString.toString();
  }

  boolean clearSelectedText(int selStart, int selEnd, StringBuffer newData)
  {
    if (selEnd > selStart)
    {
      int curMaskPos = this._curMaskPos;
      int nextMaskPos = this._nextMaskPos;
      int curMaskType = this._curMaskType;
      char curMaskChar = this._curMaskChar;
      char lastMaskChar = this._lastMaskChar;
      char decimalPoint = this._decimalPoint;
      int commandCorrection = this._commandCorrection;
      int shiftState = this._shiftState;

      setMaskPos(selStart);
      int outpos = selStart;
      char c = this._curMaskChar;
      while ((outpos < selEnd) && (c != 0)) {
        if (this._curMaskType == 1)
          c = '_';
        newData.setCharAt(outpos++, c);
        c = getNextMaskChar();
      }

      this._curMaskPos = curMaskPos;
      this._nextMaskPos = nextMaskPos;
      this._curMaskType = curMaskType;
      this._curMaskChar = curMaskChar;
      this._lastMaskChar = lastMaskChar;
      this._decimalPoint = decimalPoint;
      this._commandCorrection = commandCorrection;
      this._shiftState = shiftState;
      return true;
    }
    return false;
  }

  boolean setMaskPos(int pos)
  {
    if (pos < 0)
      return false;
    resetMaskScan();
    this._inRange = true;
    while (pos-- >= 0)
      if (getNextMaskChar() == 0)
      {
          this._inRange = false;
      }
    return this._inRange;
  }

  int nextFilterPos() {
    while (getNextMaskChar() != 0)
      if (this._curMaskType == 1)
        return this._curMaskPos - this._commandCorrection;
    return -1;
  }

  int prevFilterPos() {
    int oldPos = this._curMaskPos;
    resetMaskScan();
    int prevFilterPos = -1;
    int prevCommandCorrection = 0;
    while (nextFilterPos() != -1) {
      if (this._curMaskPos >= oldPos) {
        if (this._inRange) break;
        return this._curMaskPos - this._commandCorrection;
        //break;
      }
      prevFilterPos = this._curMaskPos;
      prevCommandCorrection = this._commandCorrection;break;
    }
    return prevFilterPos - prevCommandCorrection;
  }

  boolean matchFilter(char key)
  {
    return matchFilter(key, this._curMaskChar);
  }

  boolean matchFilter(char key, char maskChar)
  {
    switch (maskChar) {
    case '0':
      return Character.isDigit(key);
    case '9':
      return (Character.isDigit(key)) || (Character.isSpaceChar(key));
    case '#':
      return (Character.isDigit(key)) || (Character.isSpaceChar(key)) || 
        (key == '+') || (key == '-');
    case '?':
    case 'L':
      return Character.isLetter(key);
    case 'A':
    case 'a':
      return Character.isLetterOrDigit(key);
    case '&':
    case 'C':
      return !Character.isIdentifierIgnorable(key);
    }
    return false;
  }

  void resetMaskScan()
  {
    this._curMaskPos = 0;
    this._nextMaskPos = 0;
    this._lastMaskChar = '\000';
    this._commandCorrection = 0;
    this._curMaskType = 0;
    this._shiftState = 0;
  }

  char getNextMaskChar() {
    while (true) {
      if (this._nextMaskPos >= this._maskLength)
        return '\000';
      this._lastMaskChar = this._curMaskChar;
      this._curMaskChar = this._mask.charAt(this._nextMaskPos++);
      if (!isCommand()) break;
      this._commandCorrection += 1;
      if ('<' == this._curMaskChar) {
        this._shiftState = 2; continue;
      }if ('>' == this._curMaskChar) {
        this._shiftState = 1; //continue;

        break;
      }
    }
    this._curMaskType = 2;
    if ((this._lastMaskChar != '\\') && (isFilter()))
      this._curMaskType = 1;
    this._curMaskPos = (this._nextMaskPos - 1);
    return this._curMaskChar;
  }

  boolean isNavKey(int key)
  {
    switch (key) {
    default:
      return false;
    case 8:
    case 35:
    case 36:
    case 37:
    case 39:
    }
    return true;
  }

  public int lastFilterPosition()
  {
    int rawLastPosition = 
      this._filterPositions[(this._filterPositions.length - 1)];
    int lastPosition = rawLastPosition;
    for (int characterIndex = 0; characterIndex <= rawLastPosition; )
    {
      if (isCommand(getMask().charAt(characterIndex)))
        lastPosition--;
      characterIndex++;
    }

    return lastPosition;
  }
  boolean isFilter() {
    return isFilter(this._curMaskChar); } 
  boolean isCommand() { return isCommand(this._curMaskChar); } 
  boolean isMandatory() { return isMandatory(this._curMaskChar); }

  boolean isFilter(char c) {
    return -1 != "09#aAL?&C".indexOf(c); } 
  boolean isCommand(char c) { return -1 != "<>\\".indexOf(c); } 
  boolean isMandatory(char c) { return -1 != "0LA&".indexOf(c);
  }

  void init()
  {
  }
}