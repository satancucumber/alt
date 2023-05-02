package LETI.alt.controller;

import LETI.alt.models.Plot;
import LETI.alt.repo.PlotRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/plot")
public class PlotController {
    private final PlotRepo plotRepo;
    @Autowired
    public PlotController(PlotRepo plotRepo) {
        this.plotRepo = plotRepo;
    }

    @GetMapping
    public List<Plot> list() {
        return plotRepo.findAll();
    }
    @GetMapping("{id}")
    public Plot getOne(@PathVariable("id") Plot plot) {
        return plot;
    }
    @PostMapping
    public Plot create(@RequestBody Plot plot) {
        return plotRepo.save(plot);
    }
    @PutMapping("{id}")
    public Plot update(
            @PathVariable("id") Plot plotFromDb,
            @RequestBody Plot plot
    ) {
        BeanUtils.copyProperties(plot, plotFromDb, "id");
        return plotRepo.save(plotFromDb);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Plot plot) {
        plotRepo.delete(plot);
    }
}
