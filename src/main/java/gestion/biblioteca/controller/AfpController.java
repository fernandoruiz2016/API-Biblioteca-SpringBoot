package gestion.biblioteca.controller;

/*@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/afps")
public class AfpController {
    private final AfpService afpService;

    @GetMapping
    public ResponseEntity<List<AfpResponseDto>> findAll() {
        return ResponseEntity.ok(afpService.findAll());
    }

    @GetMapping("/{idAfp}")
    public ResponseEntity<AfpResponseDto> findById(@PathVariable Long idAfp) {
        return ResponseEntity.ok(afpService.findById(idAfp));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AfpRequestDto afpRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(afpService.create(afpRequestDto));
    }
}*/
