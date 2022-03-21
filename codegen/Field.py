
def camelToPascal(string):
    result = string[0].upper()
    for c in string[1:]:
        result += c
    return result

class Field:
    def __init__(self, type, name):
        self.type = type
        self.name = name

    def __str__(self) -> str:
        return f'{self.type} {self.name}'

    def init(self):
        return f'this.{self.name} = {self.name};'

    def equal(self, varName: str):
        return f'Objects.equals(this.{self.name}, {varName}.{self.name})'

    def this(self):
        return f'this.{self.name}'

    def toString(self):
        return f'"{self.name}=" + this.{self.name}'
    
    def update(self, varName, className):
        return f'{varName}.set{camelToPascal(self.name)}(new{className}.get{camelToPascal(self.name)}());'
