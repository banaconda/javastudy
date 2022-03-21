
class Controller:
    template = '''\
{package}
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.print.attribute.standard.Severity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class {className}Controller {{
    @Autowired
    {className}Service service;

    @GetMapping("/{pluralName}")
    CollectionModel<EntityModel<{className}>> all() {{

        List<EntityModel<{className}>> {pluralName} = service.get{className}();

        return CollectionModel.of({pluralName}, linkTo(methodOn({className}Controller.class).all()).withSelfRel());
    }}
    
    @PostMapping("/{pluralName}")
    ResponseEntity<?> new{className}(@RequestBody {className} new{className}) {{
        EntityModel<{className}> entityModel = service.create{className}(new{className});

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }}

    @GetMapping("/{pluralName}/{{id}}")
    EntityModel<{className}> one(@PathVariable Long id) {{
        return service.get{className}(id);
    }}

    @PutMapping("/{pluralName}/{{id}}")
    ResponseEntity<?> replace{className}(@RequestBody {className} new{className}, @PathVariable Long id) {{

        EntityModel<{className}> entityModel = service.update{className}(id, new{className});

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }}

    @DeleteMapping("/{pluralName}/{{id}}")
    ResponseEntity<?> delete{className}(@PathVariable Long id) {{
        service.delete{className}(id);
        return ResponseEntity.noContent().build();
    }}
}}
'''

    def __init__(self, package: str, className: str, pluralName: str) -> None:
        self.package = package
        self.className = className
        self.pluralName = pluralName

    def __str__(self) -> str:
        return self.template.format(
            package=self.package,
            className=self.className,
            pluralName=self.pluralName
        )
        pass
