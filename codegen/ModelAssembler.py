
class ModelAssembler:
    template = '''\
{package}

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class {className}ModelAssembler implements RepresentationModelAssembler<{className}, EntityModel<{className}>> {{

    @Override
    public EntityModel<{className}> toModel({className} {varName}) {{

        return EntityModel.of({varName}, //
                linkTo(methodOn({className}Controller.class).one({varName}.getId())).withSelfRel(),
                linkTo(methodOn({className}Controller.class).all()).withRel("{pluralName}"));
    }}
}}
'''

    def __init__(self, package: str, className: str, varName: str, pluralName: str) -> None:
        self.package = package
        self.className = className
        self.varName = varName
        self.pluralName = pluralName

    def __str__(self) -> str:
        return self.template.format(
            package=self.package,
            className=self.className,
            varName=self.varName,
            pluralName=self.pluralName
        )
