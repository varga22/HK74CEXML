<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

    <!-- 7. Autók rendszámát és árát ár szerinti sorrendben tartalmazó XML állomány -->
    <xsl:template match="/">
        <sortedAutok>
            <xsl:for-each select="autok/auto">
                <xsl:sort select="ar" order="ascending"/>
                <auto>
                    <rsz><xsl:value-of select="@rsz"/></rsz>
                    <ar><xsl:value-of select="ar"/></ar>
                </auto>
            </xsl:for-each>
        </sortedAutok>
    </xsl:template>
</xsl:stylesheet>
