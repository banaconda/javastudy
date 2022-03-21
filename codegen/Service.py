
class Service:
    template = '''\
{package}

import java.util.List;

import org.springframework.hateoas.EntityModel;

public interface {className}Service {{
    public abstract EntityModel<{className}> create{className}({className} {varName});

    public abstract EntityModel<{className}> update{className}(Long id, {className} {varName});

    public abstract void delete{className}(Long id);

    public abstract List<EntityModel<{className}>> get{className}();

    public abstract EntityModel<{className}> get{className}(Long id);
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
            varName=self.varName,
        )
