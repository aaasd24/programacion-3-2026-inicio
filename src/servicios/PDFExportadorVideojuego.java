package servicios;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.borders.SolidBorder;

// Importaciones de tus modelos
import models.Videojuego; 

public class PDFExportadorVideojuego {

    /**
     * Exporta la lista de videojuegos registrados a un documento PDF estructurado.
     */
    public void exportarVideojuegos(List<Videojuego> videojuegos, File archivo) throws IOException {
        // Configuramos el documento en orientación horizontal (Letter.rotate())
        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(archivo));
             Document doc = new Document(pdfDoc, PageSize.LETTER.rotate())) {
            
            // 1. INCORPORACIÓN DEL LOGO DE STEAK GAMES
            InputStream is = getClass().getResourceAsStream("../assets/SteakGames.png");
            if (is != null) {
                ImageData data = ImageDataFactory.create(is.readAllBytes());
                Image img = new Image(data).scaleAbsolute(50, 50);
                float altoPagina = PageSize.LETTER.rotate().getHeight();
                float margen = 40;

                img.setFixedPosition(margen, altoPagina - margen - 50);
                doc.add(img);
            }
            
            // 2. TÍTULO PRINCIPAL DEL REPORTE
            doc.add(new Paragraph("Reporte de Catálogo de Videojuegos")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER));

            // Espaciado estructural
            doc.add(new Paragraph("").setMarginTop(30));

            // 3. DEFINICIÓN DE COLUMNAS (5 Columnas con anchos proporcionales)
            // # (1), Título (4), Precio (2), Descripción (5), Crossplay (2)
            float[] columnsWidth = { 1, 4, 2, 5, 2 };
            Table tabla = new Table(UnitValue.createPercentArray(columnsWidth)).useAllAvailableWidth();

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            
            // Cabecera superior de la tabla 
            Cell cell = new Cell(1, 5)
                    .add(new Paragraph("Videojuegos en el Sistema"))
                    .setFont(font)
                    .setFontSize(14)
                    .setFontColor(DeviceGray.WHITE)
                    .setBackgroundColor(new DeviceRgb(35, 35, 35)) // Color carbon 
                    .setTextAlignment(TextAlignment.CENTER);

            tabla.addHeaderCell(cell);

            // 4. GENERACION DE CABECERAS Y PIES DE TABLA (Efecto espejo)
            for (int i = 0; i < 2; i++) {
                Cell[] headerFooter = new Cell[] {
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.85f)).add(new Paragraph("#")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.85f)).add(new Paragraph("Título")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.85f)).add(new Paragraph("Precio")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.85f)).add(new Paragraph("Descripción")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.85f)).add(new Paragraph("Crossplay"))
                };

                for (Cell celda : headerFooter) {
                    if (i == 0) {
                        tabla.addHeaderCell(celda);
                    } else {
                        tabla.addFooterCell(celda);
                    }
                }
            }
            
         // 5. INYECCIÓN DINÁMICA DE LOS REGISTROS DE LA BASE DE DATOS
            int indice = 1;
            for (Videojuego v : videojuegos) {
                // indice incremental numérico (#)
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(indice))));

                //  getTitulo()
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(v.getTitulo())));

                //  getPrecio()
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.RIGHT).add(new Paragraph("$" + String.format("%.2f", v.getPrecio()))));

                // getDescripcion() (Si es nula pone un guion)
                String desc = (v.getDescripcion() != null && !v.getDescripcion().trim().isEmpty()) ? v.getDescripcion() : "-";
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(desc)));

                // Estado de Crossplay 
                String crossplayTexto = v.getCrossplay() ? "Si" : "No";
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(crossplayTexto)));

                indice++;
            }

            // Agregamos la tabla estructurada al documento PDF
            doc.add(tabla);
        }
    }
}