from typing import List

from Field import Field


class ServiceImpl:
    template = '''\
{package}

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class {className}ServiceImpl implements {className}Service {{
    private final {className}Repository repository;
    private final {className}ModelAssembler assembler;

    {className}ServiceImpl({className}Repository repository, {className}ModelAssembler assembler) {{
        this.repository = repository;
        this.assembler = assembler;
    }}

    @Override
    public EntityModel<{className}> create{className}({className} {varName}) {{
        EntityModel<{className}> entityModel = assembler.toModel(repository.save({varName}));
        return entityModel;
    }}

    @Override
    public EntityModel<{className}> update{className}(Long id, {className} new{className}) {{
        {className} updated{className} = repository.findById(id) //
                .map({varName} -> {{
                    {updates}
                    return repository.save({varName});
                }})
                .orElseGet(() -> {{
                    new{className}.setId(id);
                    return repository.save(new{className});
                }});

        EntityModel<{className}> entityModel = assembler.toModel(updated{className});
        return entityModel;
    }}

    @Override
    public void delete{className}(Long id) {{
        {className} {varName} = repository.findById(id) //
                .orElseThrow(() -> new {className}NotFoundException(id));

        repository.deleteById(id);
    }}

    @Override
    public List<EntityModel<{className}>> get{className}() {{
        List<EntityModel<{className}>> {pluralName} = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return {pluralName};
    }}

    @Override
    public EntityModel<{className}> get{className}(Long id) {{
        {className} {varName} = repository.findById(id) //
                .orElseThrow(() -> new {className}NotFoundException(id));

        return assembler.toModel({varName});
    }}

}}
'''

    def __init__(self, package: str, className: str, varName: str, pluralName: str, fieldList: List[Field]) -> None:
        self.package = package
        self.className = className
        self.varName = varName
        self.pluralName = pluralName
        self.fieldList = fieldList

    def __str__(self) -> str:
        return self.template.format(
            package=self.package,
            className=self.className,
            varName=self.varName,
            pluralName=self.pluralName,
            updates=self.__updates()
        )
        
    def __updates(self) -> str:
        return ('\n' + ' ' * 20).join([field.update(self.varName, self.className) for field in self.fieldList])
