package dissapointinglyannotated;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;


@SupportedAnnotationTypes({
        "dissapointinglyannotated.russianstyle.Костыль",
        "dissapointinglyannotated.simple.ب_ب",
        "dissapointinglyannotated.simple.ಠ_ಠ",
        "dissapointinglyannotated.simple.ಥ_ಥ"
})
public class DissapointinglyAnnotatedProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager output = processingEnv.getMessager();

        for (TypeElement annot : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(annot)) {
                if (annot.getSimpleName().contentEquals("Костыль"))
                    output.printMessage(Diagnostic.Kind.NOTE, getElementName(e) + " is Костыль infected");
                else
                    output.printMessage(Diagnostic.Kind.NOTE, getElementName(e) + " made me roll eyes like this: " + annot.getSimpleName());
            }
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private static String getElementName(Element e) {
        if (e.getKind().equals(ElementKind.METHOD)) {
            return e.getEnclosingElement().asType().toString() + "." + e.getSimpleName();
        }
        return e.toString();
    }

}

