/*
 * Copyright 2010-2011 Joel Hockey (joel.hockey@gmail.com).  All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.java.jless.jspcsc;

import java.applet.Applet;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;


/**
 * Read file from filesystem
 * @author Joel Hockey (joel.hockey@gmail.com)
 */
public class JFileRead extends Applet {
    private static final long serialVersionUID = 0xC65DEA12462D5CB9L;

    public static String read(final String filename) throws IOException {
        try {
            return (String) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    byte[] buf = new byte[4096];
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    FileInputStream fis = new FileInputStream(filename);
                    for (int len = 0; (len = fis.read(buf)) != -1; ) {
                        baos.write(buf, 0, len);
                    }
                    fis.close();
                    return baos.toString(0);
                }
            });
        } catch (PrivilegedActionException pae) {
            throw (IOException) pae.getException();
        }
    }
}