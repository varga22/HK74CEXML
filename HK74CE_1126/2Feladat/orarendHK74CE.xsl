<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>XY Órarend – 2023/24. I. félév</title>
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
                <h1>XY Órarend – 2023/24. I. félév</h1>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Típus</th>
                            <th>Tárgy</th>
                            <th>Nap</th>
                            <th>Időtartam</th>
                            <th>Helyszín</th>
                            <th>Oktató</th>
                            <th>Szak</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="VB_orarend/ora">
                            <tr>
                                <td><xsl:value-of select="@id"/></td>
                                <td><xsl:value-of select="@típus"/></td>
                                <td><xsl:value-of select="targy"/></td>
                                <td><xsl:value-of select="idopont/nap"/></td>
                                <td>
                                    <xsl:value-of select="idopont/tol"/> - <xsl:value-of select="idopont/ig"/>
                                </td>
                                <td><xsl:value-of select="helyszin"/></td>
                                <td><xsl:value-of select="oktato"/></td>
                                <td><xsl:value-of select="szak"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
