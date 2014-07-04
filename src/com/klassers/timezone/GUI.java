package com.klassers.timezone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GUI {
	public static void create() {
		Display display = new Display();
		final Shell shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE)
				& (~SWT.MAX));
		shell.open();
		final Image image = new Image(display,
				GUI.class.getResourceAsStream("/img/gui/test.png"));
		
		Transform tr = new Transform(display);
        tr.translate(50, 120);
        tr.rotate(-30);
        GC gc = new GC(image);
        gc.setTransform(tr);
        gc.dispose();
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle clientArea = shell.getClientArea();
				e.gc.drawLine(0, 0, clientArea.width, clientArea.height);
				e.gc.drawImage(image, 5, 10);
				e.gc.drawImage(image, 0, 0, image.getBounds().width,
						image.getBounds().width, 0, 0, 100, 100);
			}
		});
		shell.setSize(300, 300);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		image.dispose();
		
	}
}
