
class NotFoundAdvice:
    template = '''\
{package}

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class {className}NotFoundAdvice {{

    @ResponseBody
    @ExceptionHandler({className}NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String switchNotFoundHandler({className}NotFoundException ex) {{
        return ex.getMessage();
    }}
}}
'''

    def __init__(self, package: str, className: str) -> None:
        self.package = package
        self.className = className

    def __str__(self) -> str:
        return self.template.format(
            package=self.package,
            className=self.className
        )
