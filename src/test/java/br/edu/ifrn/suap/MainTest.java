package br.edu.ifrn.suap;

import java.util.List;

import br.edu.ifrn.suap.model.AlunoSUAP;
import br.edu.ifrn.suap.model.ComponenteCurricularSUAP;

public class MainTest {

	public static void main(String[] args) {
		ClienteSUAP clienteSUAP = new ClienteSUAP("20161164010023", "ifrn.445566778899");
		AlunoSUAP aluno = clienteSUAP.getUsuario(AlunoSUAP.class);
		
		List<ComponenteCurricularSUAP> componentesCurriculares = aluno.getCurso().getComponentesCurriculares();
		for (ComponenteCurricularSUAP componenteCurricularSUAP : componentesCurriculares) {
			System.out.println(componenteCurricularSUAP);
		}
	}
}
