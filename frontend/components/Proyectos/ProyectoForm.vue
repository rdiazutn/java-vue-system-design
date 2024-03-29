<template>
  <TheFormDialog
    ref="form"
    v-model="showForm"
    :header-message="titleText"
    :loading="loading"
    v-bind="$attrs"
    @onConfirm="saveOrUpdate"
    @onCancel="closeForm"
    v-on="$listeners"
  >
    <template #activator="{on}">
      <TheCreateButton
        class="my-4"
        :inner-text="titleText"
        :disabled="disabled"
        icon="mdi-plus"
        color="accent"
        v-on="on"
      />
    </template>
    <template>
      <v-card outlined class="mb-2">
        <v-card-title>
          {{ $t('proyectos.titulo_creacion') }}
        </v-card-title>
        <v-row class="px-2">
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="proyectoFinanciamiento.entidadRealizadora"
              item-text="nombre"
              :get-items-function="$entidadService.getEntidades"
              :label="$t('proyectos.entidad')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="proyectoFinanciamiento.director"
              item-text="nombre"
              :get-items-function="$userService.getUsers"
              :label="$t('proyectos.director')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheTextInput
              v-model="proyectoFinanciamiento.presupuestosMinimos"
              :label="$t('proyectos.presupuestos_exigibles')"
              :rules="[$rl.required(),$rl.positiveOrZero()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheTextInput
              v-model="proyectoFinanciamiento.montoMaximoSinPresupuestos"
              :label="$t('proyectos.monto_maximo_sin_presupuestos')"
              :rules="[$rl.required(),$rl.positiveOrZero()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheTextInput
              v-model="proyectoFinanciamiento.montoInicialAsignado"
              :label="$t('proyectos.monto_inicial')"
              :rules="[$rl.required(),$rl.positiveOrZero()]"
            />
          </TheResponsiveColumn>
        </v-row>
      </v-card>
    </template>
  </TheFormDialog>
</template>
<script>
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheCreateButton from '~/components/General/Buttons/TheCreateButton'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
export default {
  components: {
    TheFormDialog,
    TheCreateButton,
    TheTextInput,
    TheAsyncAutocompleteInput
  },
  props: {
    item: {
      type: Object,
      default: () => ({})
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      loading: false,
      showForm: false,
      proyectoFinanciamiento: {
        entidadRealizadora: {},
        director: {}
      }
    }
  },
  computed: {
    titleText () {
      return this.$t('proyectos.crear')
    }
  },
  methods: {
    saveOrUpdate () {
      this.loading = true
      this.$proyectoFinanciamientoService.crearProyecto(this.proyectoFinanciamiento)
        .then((response) => {
          if (response) {
            this.closeForm()
            this.toastSuccess(this.$t('saved-ok'))
            this.$emit('created', response)
          }
        })
        .finally(() => { this.loading = false })
    },
    closeForm () {
      this.proyectoFinanciamiento = {
        entidadRealizadora: {},
        director: {}
      }
      this.$refs.form.resetValidation()
      this.showForm = false
    }
  }
}
</script>
