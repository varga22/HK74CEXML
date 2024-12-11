<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Hallgatók adatai</title>
                <style>
                    table {
                        border-collapse: collapse;
                        width: 100%;
                    }
                    th, td {
                        border: 1px solid black;
                        padding: 8px;
                        text-align: left;
                    }
                    th {
                        background-color: #f2f2f2;
                    }
                </style>
            </head>
            <body>
                <h1>Hallgatók adatai</h1>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Keresztnév</th>
                            <th>Vezetéknév</th>
                            <th>Becenév</th>
                            <th>Kor</th>
                            <th>Ösztöndíj</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="class/student">
                            <tr>
                                <td><xsl:value-of select="@id"/></td>
                                <td><xsl:value-of select="keresztnev"/></td>
                                <td><xsl:value-of select="vezeteknev"/></td>
                                <td><xsl:value-of select="becenev"/></td>
                                <td><xsl:value-of select="kor"/></td>
                                <td><xsl:value-of select="osztondij"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
