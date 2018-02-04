<%@ attribute name="title"%>
<%@ attribute name="color"%>
<%@ attribute name="bgcolor"%>

<table border="1px" bgcolor="${color}">
    <tr>
        <td>
            ${title}
        </td>
    </tr>
    <tr>
        <td bgcolor="${bgcolor}"><jsp:doBody/></td>
    </tr>
</table>
