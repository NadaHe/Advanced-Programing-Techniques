import java.io.*;
import java.util.Arrays;

import javax.servlet.*;
import javax.servlet.http.*;

public class Matrix extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

///////////////////////////////////////////////////////////////////////////////////////

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Process the matrix

		String matrixData = request.getParameter("matrix");

		String body = null;

		if (matrixData.trim().equals("")) {
			body = "<p>No matrix provided.</p>";
		} else {
			String[] rows = matrixData.split("\\r?\\n");

			int n = rows.length;
			int m = rows[0].split(" ").length;
			int[][] matrix = new int[n][m];
			for (int i = 0; i < n; i++) {
				String[] cols = rows[i].split(" ");
				if (cols.length > 0) {
					for (int j = 0; j < m; j++) {
						matrix[i][j] = Integer.parseInt(cols[j]);
					}
				}
			}
			body = "<div id=\"OriginalMatrix\">" + getMatrixHTML(matrix) + "</div>";

			// Process the user choice
			String transposeValue = request.getParameter("Transpose");
			boolean doTranspose = transposeValue == null ? false : true;

			String IsIdentity = request.getParameter("IsIdentity");
			boolean doIsIdentity = IsIdentity == null ? false : true;

			if (doTranspose) {
				body += " <h2>Transpose</h2> <div id=\"MatrixTranspose\"> ";
				int[][] transpose = getTranspose(matrix);
				body += getMatrixHTML(transpose);
				body += "</div>";
			}

			if (doIsIdentity) {
				body += "<h2>Identity</h2>";
				boolean result = isIdentityMatrix(matrix);
				if (result == true) {
					body += " <div>  true";
					body += "</div>";
				} else {
					body += "false";
					body += "</div>";
				}
			}
		}

		response.setContentType("text/html");
		String page = "<!doctype html> <html> <body bgcolor=\"#f0f0f0\" align=\"center\"><h1>Result</h1><h2>Original Matrix</h2>"
				+ body + "</body></html>";
		response.getWriter().println(page);
	}

	/////////////////////////////////////////////////////////////////////////////////////

	private String getMatrixHTML(int[][] matrix) {
		String body = "";
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			String row = Arrays.toString(matrix[i]);
			body += "<p>" + row.substring(1, row.length() - 1).replace(",", "") + "</p>";
			body += "\n";
		}
		return body;
	}

	/////////////////////////////////////////////////////////////////////////////////////

	private int[][] getTranspose(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int transpose[][] = new int[cols][rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				transpose[j][i] = matrix[i][j];
			}
		}
		return transpose;
	}

	///////////////////////////////////////////////////////////////////////////////////////

	// Helper method to check if matrix is an identity matrix
	private boolean isIdentityMatrix(int[][] matrix) {

		if (matrix == null) {
			return false;
		}
		int size = matrix.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j && matrix[i][j] != 1) {
					return false;
				} else if (i != j && matrix[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
}