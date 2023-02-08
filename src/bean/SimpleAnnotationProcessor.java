package bean;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes( "com.javacodegeeks.advanced.processor.Immutable" )
@SupportedSourceVersion( SourceVersion.RELEASE_7 )

public class SimpleAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(final Set<? extends TypeElement> annotations,final RoundEnvironment roundEnv) {
        for (final Element element : roundEnv.getElementsAnnotatedWith(NotNull.class)) {
            System.out.println("Element:"+element.getClass().getName());
            if (element instanceof TypeElement) {
                final TypeElement typeElement = (TypeElement) element;

                for (final Element eclosedElement : typeElement.getEnclosedElements()) {
                    if (eclosedElement instanceof VariableElement) {
                        final VariableElement variableElement = (VariableElement) eclosedElement;

                        if (variableElement.getConstantValue() == null) {
                            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Bosh olmaz");
                        }
                    }
                }
            }
        }
        return true;
    }
}
