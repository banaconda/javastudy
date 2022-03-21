from typing import List
from Field import Field

class Entity:
    template = '''\
{package}
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
class {className} {{

    private @Id @GeneratedValue Long id;
    {fields}

    {className}() {{
    }}

    {className}({params}) {{
        {inits}
    }}
    
    @Override
    public boolean equals(Object o) {{

        if (this == o)
            return true;
        if (!(o instanceof {className}))
            return false;
        {className} {varName} = ({className}) o;
        return {equals}
    }}

    @Override
    public int hashCode() {{
        return {hash}
    }}

    @Override
    public String toString() {{
        return {toStrings}
    }}
}}'''

    def __init__(self, package: str, className: str, varName: str, fieldList: List[Field]) -> None:
        self.package = package
        self.className = className
        self.varName = varName
        self.fieldList = fieldList

    def __str__(self) -> str:
        return self.template.format(
            package=self.package,
            className=self.className,
            varName=self.varName,
            fields=self.__fields(),
            params=self.__params(),
            inits=self.__inits(),
            equals=self.__equals(),
            hash=self.__hash(),
            toStrings=self.__toStrings()
        )
        pass

    def __fields(self) -> str:
        return ('\n' + ' ' * 4).join([f'{field};' for field in self.fieldList]) + '\n'

    def __params(self) -> str:
        return (', ').join([str(field)for field in self.fieldList])

    def __inits(self) -> str:
        return ('\n' + ' ' * 8).join([field.init() for field in self.fieldList]) + '\n'

    def __equals(self) -> str:
        return (' && ').join([field.equal(self.varName) for field in self.fieldList]) + ';'

    def __hash(self) -> str:
        return 'Objects.hash(' + ', '.join([field.this() for field in self.fieldList]) + ');'

    def __toStrings(self) -> str:
        return f'"{self.className}{{" + ' + (' + ", " + ').join([field.toString() for field in self.fieldList]) + ' + "}";'
