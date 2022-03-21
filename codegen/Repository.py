
class Repository:
    template = '''\
{package}

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface {className}Repository extends JpaRepository<{className}, Long> {{
    
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
