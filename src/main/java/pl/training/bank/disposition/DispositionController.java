package pl.training.bank.disposition;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.training.bank.common.UriBuilder;
import pl.training.bank.common.mapper.Mapper;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/dispositions")
public class DispositionController {

    @NonNull
    private Mapper mapper;
    @NonNull
    private DispositionService dispositionService;
    private UriBuilder uriBuilder = new UriBuilder();

    @RequestMapping(method =RequestMethod.POST)
    public ResponseEntity process(@RequestBody DispositionTo dispositionTo){
        Disposition disposition = mapper.map(dispositionTo, Disposition.class);
        dispositionService.process(disposition);
        return ResponseEntity.noContent().build();
    }
}
