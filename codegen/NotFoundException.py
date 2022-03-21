
class NotFoundException:
    template = '''\
{package}

class {className}NotFoundException extends RuntimeException {{

    {className}NotFoundException(Long id) {{
        super("Could not find {varName} " + id);
    }}
}}
'''

    def __init__(self, package: str, className: str, varName: str) -> None:
        self.package = package
        self.className = className
        self.varName = varName

    def __str__(self) -> str:
        return self.template.format(
            package=self.package,
            className=self.className,
            varName=self.varName
        )
