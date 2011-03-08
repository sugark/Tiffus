# Project Aim
Create client side pure Javascript imaging library to load, save and manipulate binary images. Library is written in GWT JAVA and converted to Javascript. In the future on thick client Browser OS Javascript performance will be as fast as Java could.

# How it works
1. Source image downloaded as Binary Data using XMLHttpRequest with MimeType('text/plain; charset=x-user-defined'); (future: HTML5 CanvasPixelArray)
1. Imageprocessing using Tiffus
1. Desination image shown as Data URI scheme (future: HTML5 CanvasPixelArray)

# Current image functions
* load
* save
* resize
* flip
* invert color

# Supported file formats
 * Windows BMP (no compression, RLE)
 * OS/2 BMP
 * ICO
 * GIF
 * JPEG (pure Java, **no Native Java**, no java.util.zip, no com.sun.image.codec.jpeg)
 * PNG (pure Java, **no Native java**)
 * TIFF (supported compressions: no compression, PACKBITS, **DEFLATE**, CCITT Group 3. 1-D and 2-D (**T4**), CCITT Group 4. 2-D (**T6**), **LZW**, pure Java, **no Native Java**)

# Project original aim was
No browser supports TIFF images! Project aim was to create plain Javascript chrome extension which can convert single/multi page TIFF image/s to BMP/GIF image. That is the name come from.

Project was build on org.eclipse.swt library using Java Advanced Imaging, jcraft.zlib components.

# Current status
As a GWT project it runs super fast in Development Mode (Java VM), but quite slow in Javascript on huge files (e.g. PNG save on 2500 x 3500). Hopefully performance could be enhanced.

Paralelly I am on to migrate complete Java Advanced Imaging (JAI) library to GWT.

Regards,
Sugar Krisztian from phrox